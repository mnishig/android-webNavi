package red.txn.webNavi;

import android.app.Application;

import com.alicecallsbob.assist.sdk.core.AssistApplication;
import com.alicecallsbob.assist.sdk.core.AssistCore;
import com.alicecallsbob.assist.sdk.core.AssistCoreImpl;

public class La365App extends Application implements AssistApplication {

    private AssistCore assistCore_;

    private void createAssistCore(Application application) {
        assistCore_ = new AssistCoreImpl(application);
    }

    private void terminateAssistCore() {
        assistCore_.terminate();
        assistCore_ = null;
    }

    @Override
    public AssistCore getAssistCore() {
        return assistCore_;
    }

}
