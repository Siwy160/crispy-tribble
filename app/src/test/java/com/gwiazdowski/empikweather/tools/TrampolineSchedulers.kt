package com.gwiazdowski.empikweather.tools

import com.gwiazdowski.services.schedulers.IRxSchedulers
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TrampolineSchedulers : IRxSchedulers {

    override fun io(): Scheduler = Schedulers.trampoline()
    override fun main(): Scheduler = Schedulers.trampoline()
}