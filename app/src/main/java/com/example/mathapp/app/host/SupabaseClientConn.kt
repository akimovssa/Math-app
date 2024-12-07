package com.example.mathapp.app.host

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.FlowType
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

object SupabaseClientConn {

    private val client: SupabaseClient = createSupabaseClient(
        supabaseUrl = "https://runvwdtynqykyljedgem.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InJ1bnZ3ZHR5bnF5a3lsamVkZ2VtIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzM0MDA4NzAsImV4cCI6MjA0ODk3Njg3MH0.UaB70k4K9pZq98C7p1q_Smhahje96AATusQlwGDKMBE"
    ) {
        install(Postgrest)
        install(io.github.jan.supabase.storage.Storage)
        install(Auth) {
            flowType = FlowType.PKCE
        }
    }

    fun getClient() = client
}
