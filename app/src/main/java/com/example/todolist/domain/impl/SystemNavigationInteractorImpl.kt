package com.example.todolist.domain.impl

import com.example.todolist.domain.interactor.SystemNavigationInteractor
import com.example.todolist.domain.repository.SystemNavigatorRepository

class SystemNavigationInteractorImpl(
    private val systemNavigatorRepository: SystemNavigatorRepository
) :
    SystemNavigationInteractor {
    override suspend fun openSettings() {
        systemNavigatorRepository.openSettings()
    }
}