package com.test.app;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.test.app.data.ArticleData;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    EditText edt_search;

    @Rule
    public ActivityTestRule<MainActivity> main = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.test.app", appContext.getPackageName());
    }

    @Test
    public void getListViewDetails() throws Exception {
        ListView listview = (ListView) main.getActivity().findViewById(R.id.lst_articles);
        assertThat(listview.getCount(), is(10));
    }

    @Test
    public void testShouldShowTheItemDetailWhenAnItemIsClicked() throws Exception {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        final ListView listview = (ListView) main.getActivity().findViewById(R.id.lst_articles);

        instrumentation.runOnMainSync(new Runnable() {
            @Override
            public void run() {
                int position = 0;
                listview.performItemClick(listview.getChildAt(position), position, listview.getAdapter().getItemId(position));
            }
        });

        Instrumentation.ActivityMonitor monitor = instrumentation.addMonitor(ArticleData.class.getName(), null, false);
        Activity itemDetailActivity = instrumentation.waitForMonitorWithTimeout(monitor, 5000);

        TextView detailView = (TextView) itemDetailActivity.findViewById(R.id.txt_title_detail);
        assertThat(detailView.getText().toString(), is("Item 1"));
    }

    @Test
    public void checkEditText() {
        assertNotNull(edt_search);
    }

    @Test
    public void testText() {
        assertEquals("", edt_search.getText());

    }

}
