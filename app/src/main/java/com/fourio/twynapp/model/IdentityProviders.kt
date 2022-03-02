package com.fourio.twynapp.model

class IdentityProviders(val identity_providers: List<Providers>) {
    class Providers(val name: String, val logo: String, var selected: Boolean = false)
}