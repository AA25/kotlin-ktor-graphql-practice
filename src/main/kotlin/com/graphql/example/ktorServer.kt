package com.graphql.example

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*

/**
 * The Ktor specific code to handle incoming [ApplicationCall]s, send them to GraphQL,
 * and then format and send a correct response back.
 */

class KtorServer {
    private val mapper = jacksonObjectMapper()
    private val ktorGraphQLServer = getGraphQLServer(mapper)

    /**
     * Handle incoming Ktor Http requests and send them back to the response methods.
     */
    @Suppress("BlockingMethodInNonBlockingContext")
    suspend fun handle(applicationCall: ApplicationCall) {
        // Execute the query against the schema which will return a GQL response
        val result = ktorGraphQLServer.execute(applicationCall.request)

        if (result != null) {
            // write response as json
            val json = mapper.writeValueAsString(result.response)
            applicationCall.response.call.respond(json)
        } else {
            applicationCall.response.call.respond(HttpStatusCode.BadRequest, "Invalid request")
        }
    }
}
