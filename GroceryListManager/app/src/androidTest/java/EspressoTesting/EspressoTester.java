package EspressoTesting;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openContextualActionModeOverflowMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.doubleClick;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static androidx.test.espresso.assertion.PositionAssertions.*;
import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import android.view.View;
import android.widget.EditText;
import edu.qc.seclass.glm.MainActivity;
import com.example.grocerylistmanager.R;


import java.util.Random;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
@LargeTest



public class EspressoTester {

    private View decorView;
    private String newReminderListName, newUsername, newPassword;

    @Before
    public void initGroceryList() {
        newReminderListName = "";
        newUsername="";
        newPassword="";
    }

    private void generateRandomList(){
        int AlphabetStartLimit = 97;
        int AlphabetEndLimit = 122;
        int targetStringLength = 7;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = AlphabetStartLimit + (int)
                    (random.nextFloat() * (AlphabetEndLimit - AlphabetStartLimit + 1));
            buffer.append((char) randomLimitedInt);
        }

        newReminderListName = buffer.toString();
    }

    private void generateUsername(){
        int AlphabetStartLimit = 97;
        int AlphabetEndLimit = 122;
        int targetStringLength = 5;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = AlphabetStartLimit + (int)
                    (random.nextFloat() * (AlphabetEndLimit - AlphabetStartLimit + 1));
            buffer.append((char) randomLimitedInt);
        }

        newUsername = buffer.toString();
    }

    private void generatePassword(){
        int AlphabetStartLimit = 97;
        int AlphabetEndLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = AlphabetStartLimit + (int)
                    (random.nextFloat() * (AlphabetEndLimit - AlphabetStartLimit + 1));
            buffer.append((char) randomLimitedInt);
        }

        newPassword = buffer.toString();
    }

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);



    @Test
    public void A_AppTitleIsDisplayed(){//tests if app launched correctly.
        onView(withText("GroceryList Manager")).check(matches(isDisplayed()));
    }

    @Test
    public void G_CreateAListWithRandomName(){//tests if app launched correctly.
        generateRandomList();
        onView(withId(R.id.username)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.createListBtn)).perform(click());
        onView(withId(R.id.createGroceryListEditText)).perform(typeText(newReminderListName));
        //closeSoftKeyboard();
        Espresso.pressBack();
        onView(withText("Create List")).inRoot(isDialog()).perform(click());
        onView(withId(R.id.removeButton)).perform(click());
        onView(withText("Delete List")).inRoot(isDialog()).perform(click());    }

    @Test
    public void G_CreateAListWithRandomName2(){//tests if app launched correctly.
        generateRandomList();
        onView(withId(R.id.username)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.createListBtn)).perform(click());
        onView(withId(R.id.createGroceryListEditText)).perform(typeText(newReminderListName));
        //closeSoftKeyboard();
        Espresso.pressBack();
        onView(withText("Create List")).inRoot(isDialog()).perform(click());
        onView(withId(R.id.removeButton)).perform(click());
        onView(withText("Delete List")).inRoot(isDialog()).perform(click());    }

    @Test
    public void B_WrongUsernameandPass(){//tests if wrong username and password gives Login failed
        onView(withId(R.id.username)).perform(typeText("apple"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("pear"), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        onView(withText("GroceryList Manager")).check(matches(isDisplayed()));
    }

    @Test
    public void B_Random1WrongUsernameandPass(){//tests if wrong username and password gives Login failed
        generateUsername();
        generatePassword();
        onView(withId(R.id.username)).perform(typeText(newUsername), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(newPassword), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        onView(withText("GroceryList Manager")).check(matches(isDisplayed()));
    }

    @Test
    public void B_Random2WrongUsernameandPass(){//tests if wrong username and password gives Login failed
        generateUsername();
        generatePassword();
        onView(withId(R.id.username)).perform(typeText(newUsername), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(newPassword), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        onView(withText("GroceryList Manager")).check(matches(isDisplayed()));
    }

    @Test
    public void C_LoginSuccessfully(){//logins in with correct username and password
    onView(withId(R.id.username)).perform(typeText("admin"), closeSoftKeyboard());
    onView(withId(R.id.password)).perform(typeText("admin"), closeSoftKeyboard());
    onView(withId(R.id.loginButton)).perform(click());
    onView(withText("Grocery Lists Mangement")).check(matches(isDisplayed()));

    }
    @Test
    public void G_CreateAList(){//logins in with correct username and password and creates a grocery list
        onView(withId(R.id.username)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.createListBtn)).perform(click());
        onView(withId(R.id.createGroceryListEditText)).perform(typeText("TestList1"));
        //closeSoftKeyboard();
        Espresso.pressBack();
        onView(withText("Create List")).inRoot(isDialog()).perform(click());
        onView(withId(R.id.removeButton)).perform(click());
        onView(withText("Delete List")).inRoot(isDialog()).perform(click());
    }
    @Test
    public void F_RenamingList() {//renames an already existing list
        onView(withId(R.id.username)).perform(typeText("admin"), closeSoftKeyboard());
        //onView(withText(R.id.password)).perform(click());
        onView(withId(R.id.password)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.createListBtn)).perform(click());
        onView(withId(R.id.createGroceryListEditText)).perform(typeText("TestList2"));
        //closeSoftKeyboard();
        Espresso.pressBack();
        onView(withText("Create List")).inRoot(isDialog()).perform(click());
        onView(withId(R.id.renameButton)).perform(click());
        onView(withId(R.id.renameGroceryListEditText)).perform(typeText("TestList2Renamed"));
        //closeSoftKeyboard();
        Espresso.pressBack();
        onView(withText("Rename List")).inRoot(isDialog()).perform(click());
        onView(withId(R.id.removeButton)).perform(click());
        onView(withText("Delete List")).inRoot(isDialog()).perform(click());
    }

    @Test
    public void D_UILoginTest(){//tests the UI of the launch screen
        onView(withId(R.id.username)).check(isCompletelyAbove(withId(R.id.password)));
    }
    @Test
    public void L_ItemsAddedToList(){//attempts to add items to the list
        onView(withId(R.id.username)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.createListBtn)).perform(click());
        onView(withId(R.id.createGroceryListEditText)).perform(typeText("ListToTestAddingItems"));
        //closeSoftKeyboard();
        Espresso.pressBack();
        onView(withText("Create List")).inRoot(isDialog()).perform(click());
        onView(withSubstring("ListToTestAddingItems")).perform(click());
        //onView(hasSibling(withText("ListToTestAddingItems"))).perform(click());


    }
    @Test
    public void H_UItestforRenameAndDelete(){//tests the UI of the rename and delete
        onView(withId(R.id.username)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.createListBtn)).perform(click());
        onView(withId(R.id.createGroceryListEditText)).perform(typeText("ListMadeToTestUI"));
        //closeSoftKeyboard();
        Espresso.pressBack();
        onView(withText("Create List")).inRoot(isDialog()).perform(click());
        onView(withId(R.id.renameButton)).check(isCompletelyAbove(withId(R.id.removeButton)));
        onView(withId(R.id.removeButton)).perform(click());
        onView(withText("Delete List")).inRoot(isDialog()).perform(click());
    }
    @Test
    public void I_UITestForItems(){//tests the UI of the launch screen
        onView(withId(R.id.username)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.createListBtn)).perform(click());
        onView(withId(R.id.createGroceryListEditText)).perform(typeText("ListToTestAddingItems"));
        //closeSoftKeyboard();
        Espresso.pressBack();
        onView(withText("Create List")).inRoot(isDialog()).perform(click());
        onView(withSubstring("ListToTestAddingItems")).perform(click());
        onView(withId(R.id.clearAll)).check(isCompletelyLeftOf(withId(R.id.addByType)));
        onView(withId(R.id.addByType)).check(isCompletelyLeftOf(withId(R.id.browseItemsButton)));
        Espresso.pressBack();
        onView(withId(R.id.removeButton)).perform(click());
        onView(withText("Delete List")).inRoot(isDialog()).perform(click());
    }
@Test
public void E_DeletingList(){
    onView(withId(R.id.username)).perform(typeText("admin"), closeSoftKeyboard());
    onView(withId(R.id.password)).perform(typeText("admin"), closeSoftKeyboard());
    onView(withId(R.id.loginButton)).perform(click());
    onView(withId(R.id.createListBtn)).perform(click());
    onView(withId(R.id.createGroceryListEditText)).perform(typeText("ListToBeDeleted"));
    //closeSoftKeyboard();
    Espresso.pressBack();
    onView(withText("Create List")).perform(click());
    onView(withId(R.id.removeButton)).perform(click());
    onView(withText("Delete List")).inRoot(isDialog()).perform(click());
}
/*    @Test
    public void J_CheckTest(){//deletes an already existing list
        onView(withId(R.id.username)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.createListBtn)).perform(click());
        onView(withId(R.id.createGroceryListEditText)).perform(typeText("ListToTestAddingItems"));
        closeSoftKeyboard();
        onView(withText("Create List")).inRoot(isDialog()).perform(click());
        onView(withSubstring("ListToTestAddingItems")).perform(click());
        //onView(hasSibling(withText("ListToTestAddingItems"))).perform(click());

    }*/
    @Test
    public void K_BrowseItem(){
        onView(withId(R.id.username)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.createListBtn)).perform(click());
        onView(withId(R.id.createGroceryListEditText)).perform(typeText("ListToTestIfYouCanBrowseItems"));
        //closeSoftKeyboard();
        Espresso.pressBack();
        onView(withText("Create List")).inRoot(isDialog()).perform(click());
        onView(withSubstring("ListToTestIfYouCanBrowseItems")).perform(click());
        //onView(hasSibling(withText("ListToTestAddingItems"))).perform(click());
        onView(withId(R.id.browseItemsButton)).perform(click());
        Espresso.pressBack();
        Espresso.pressBack();
        onView(withId(R.id.removeButton)).perform(click());
        onView(withText("Delete List")).inRoot(isDialog()).perform(click());
    }
}
