package me.androiddev.logintestapp.simulation

object ApiSimulationClass {

     fun login(login: String, password: String): Boolean {
        Thread.sleep(3000)
        return login == "test" && password == "12345"
    }
}