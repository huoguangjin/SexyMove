package com.github.huoguangjin.sexymove.services

import com.intellij.openapi.project.Project
import com.github.huoguangjin.sexymove.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
