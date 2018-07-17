package com.example.mma.mmhealthcare.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.mma.mmhealthcare.R
import com.example.mma.mmhealthcare.adapters.HealthCareAdapter
import com.example.mma.mmhealthcare.components.SmartScrollListener
import com.example.mma.mmhealthcare.data.model.HealthCareAppModel
import com.example.mma.mmhealthcare.data.vos.HealthCareInfoVO
import com.example.mma.mmhealthcare.delegates.HealthCareItemDelegate
import com.example.mma.mmhealthcare.events.DataEvent
import com.example.mma.mmhealthcare.events.ErrorEvent

import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : BaseActivity(), HealthCareItemDelegate {

    private var mHealthCareAdapter: HealthCareAdapter? = null
    private var mSmartScrollListener: SmartScrollListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayShowTitleEnabled(false)

        var healthcare: HealthCareInfoVO = HealthCareInfoVO()
        healthcare.healthCareId = "PADC-23445"
        healthcare.title = "title"
        healthcare.shortDescription = "shortDescription"
        healthcare.publishedDate = "publishedDate"
        healthcare.image = "Images"

        rvHealthCare.setEmptyView(vpEmptyHealthCare)
        rvHealthCare.layoutManager = LinearLayoutManager(applicationContext)

       /* mHealthCareAdapter = HealthCareAdapter(applicationContext, this)
        rvHealthCare.adapter = mHealthCareAdapter*/

        mSmartScrollListener = SmartScrollListener(object : SmartScrollListener.OnSmartScrollListener {
            override fun onListEndReach() {
                Snackbar.make(rvHealthCare, "Loading more data.", Snackbar.LENGTH_LONG).show()
                swipeRefreshLayout.isRefreshing = true
                HealthCareAppModel.getInstance().loadHealthCare()
            }
        })
        rvHealthCare.addOnScrollListener(mSmartScrollListener)

        mHealthCareAdapter = HealthCareAdapter(applicationContext, this)
        rvHealthCare.adapter = mHealthCareAdapter

        swipeRefreshLayout.isRefreshing = true
        HealthCareAppModel.getInstance().loadHealthCare()

        swipeRefreshLayout.setOnRefreshListener {
            val healthcareAdapterVal = mHealthCareAdapter
            healthcareAdapterVal!!.clearData()
            HealthCareAppModel.getInstance().forceLoadHealthCare()
        }
    }

    override fun onTapHealthCare(healthCare: HealthCareInfoVO?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTapComment() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTapLike() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onHealthCareLoadedEvent(healcareLoadedEvent: DataEvent.HealthCareLoadedEvent) {
        swipeRefreshLayout.isRefreshing = false;
        mHealthCareAdapter!!.appendHealthCareData(healcareLoadedEvent.loadedHealthCare as MutableList<HealthCareInfoVO>)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onErrorHealthCareLoadedEvent(apiErrorEvent: ErrorEvent.ApiErrorEvent) {
        swipeRefreshLayout.isRefreshing = false
        Snackbar.make(rvHealthCare, "ERROR : " + apiErrorEvent.getMsg(), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEmptyHealthCareLoadedLayout(emptyDataLoadedEvent: DataEvent.EmptyDataLoadedEvent) {
        swipeRefreshLayout.isRefreshing = false
        Snackbar.make(rvHealthCare, "ERROR : " + emptyDataLoadedEvent.errorMsg, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
    }

}
