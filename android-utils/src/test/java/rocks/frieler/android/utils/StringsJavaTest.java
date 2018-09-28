package rocks.frieler.android.utils;

import org.junit.Test;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StringsJavaTest {
    @Test
    public void testJoinWithJavaTypes() {
        String joined = Strings.join(Collections.singletonList(this), "", "", "", Object::toString);

        assertThat(joined, is(this.toString()));
    }
}
