package com.gwiazdowski.services.schedulers

import io.reactivex.Scheduler

interface IRxSchedulers {
    fun main(): Scheduler
    fun io(): Scheduler
}