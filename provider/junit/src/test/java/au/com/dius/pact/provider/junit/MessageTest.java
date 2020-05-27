package au.com.dius.pact.provider.junit;

import au.com.dius.pact.provider.PactVerifyProvider;
import au.com.dius.pact.provider.junit.target.MessageTarget;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import au.com.dius.pact.provider.junitsupport.target.Target;
import au.com.dius.pact.provider.junitsupport.target.TestTarget;
import org.junit.runner.RunWith;

@RunWith(PactRunner.class)
@Provider("AmqpProvider")
@PactFolder("src/test/resources/amqp_pacts")
public class MessageTest {
  @TestTarget
  public final Target target = new MessageTarget();

  @State("SomeProviderState")
  public void someProviderState() {}

  @PactVerifyProvider("a test message")
  public String verifyMessageForOrder() {
    return "{\"testParam1\": \"value1\",\"testParam2\": \"value2\"}";
  }
}
