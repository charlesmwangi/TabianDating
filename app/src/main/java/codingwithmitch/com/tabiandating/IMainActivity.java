package codingwithmitch.com.tabiandating;

import codingwithmitch.com.tabiandating.models.Message;
import codingwithmitch.com.tabiandating.models.User;

public interface IMainActivity {
    //inflate view fragment
    void inflateViewProfileFragment(User user);

    void onMessageSelected(Message message);
}
