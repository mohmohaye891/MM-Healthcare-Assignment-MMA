package com.example.mma.mmhealthcare.events

class ErrorEvent {

    class ApiErrorEvent(val throwable: Throwable? = null) {

        fun getMsg(): String? {
            if (throwable == null)
                return "Null Throwable in Error"
            else
                return throwable.message
        }
    }
}