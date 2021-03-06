package au.com.dius.pact.consumer

import au.com.dius.pact.core.matchers.Mismatch
import au.com.dius.pact.core.model.Request

sealed class PactVerificationResult {
  open fun getDescription() = toString()

  data class Ok(val result: Any? = null) : PactVerificationResult()

  data class Error(val error: Throwable, val mockServerState: PactVerificationResult) : PactVerificationResult()

  data class PartialMismatch(val mismatches: List<Mismatch>) : PactVerificationResult() {
    override fun getDescription(): String {
      return mismatches.joinToString("\n") {
        it.description()
      }
    }
  }

  data class Mismatches(val mismatches: List<PactVerificationResult>) : PactVerificationResult() {
    override fun getDescription(): String {
      return "The following mismatched requests occurred:\n" +
        mismatches.joinToString("\n", transform = PactVerificationResult::getDescription)
    }
  }

  data class UnexpectedRequest(val request: Request) : PactVerificationResult() {
    override fun getDescription() = "Unexpected Request:\n$request"
  }

  data class ExpectedButNotReceived(val expectedRequests: List<Request>) : PactVerificationResult() {
    override fun getDescription(): String {
      return "The following requests were not received:\n" + expectedRequests.joinToString("\n")
    }
  }
}
