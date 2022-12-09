package EspressoTesting;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static androidx.test.espresso.assertion.PositionAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import android.view.View;
import edu.qc.seclass.glm.MainActivity;
import edu.qc.seclass.glm.R;
//import com.example.grocerylistmanager.R;


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

    @Test
    public void J_CheckTestonListApple(){//deletes an already existing list
        onView(withId(R.id.username)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.createListBtn)).perform(click());
        onView(withId(R.id.createGroceryListEditText)).perform(typeText("ListToTestCheckingItems"));
        Espresso.pressBack();
        onView(withText("Create List")).inRoot(isDialog()).perform(click());
        onView(withSubstring("ListToTestCheckingItems")).perform(click());
        onView(withId(R.id.browseItemsButton)).perform(click());
        onView(withSubstring("Fruits")).perform(click());
        onView(allOf(withId(R.id.itemAddToListButton), hasSibling(withText("1. Apples")))).perform(click());
        //onView(withId(R.id.itemAddToListButton),hasSibling(withText("1. Apples")));
        //onView(allOf(withText("Add this item"),isDescendantOfA(withText("Apples")))).perform(click());
        // onView(withId(R.id.itemAddToListButton)).perform(click());
        onView(withId(R.id.addItemQuantityEditText)).perform(typeText("3"));
        Espresso.pressBack();
        onView(withId(R.id.addItemUnitTypeEditText)).perform(typeText("pounds"));
        Espresso.pressBack();
        onView(withText("ADD ITEM TO LIST")).inRoot(isDialog()).perform(click());
        onView(withId(R.id.isCheckedCheckBox)).perform(click());
        Espresso.pressBack();
        Espresso.pressBack();
        Espresso.pressBack();
        //onView(allOf(withId(R.id.removeButton), hasSibling(withText("ListToTestCheckingItems")))).perform(click());
        onView(withId(R.id.removeButton)).perform(click());
        onView(withText("Delete List")).inRoot(isDialog()).perform(click());
    }
    @Test
    public void J_CheckTestonListHamburgers(){//deletes an already existing list
        onView(withId(R.id.username)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.createListBtn)).perform(click());
        onView(withId(R.id.createGroceryListEditText)).perform(typeText("ListToTestCheckingItems2"));
        Espresso.pressBack();
        onView(withText("Create List")).inRoot(isDialog()).perform(click());
        onView(withSubstring("ListToTestCheckingItems2")).perform(click());
        onView(withId(R.id.browseItemsButton)).perform(click());
        onView(withSubstring("Meat")).perform(click());
        onView(allOf(withId(R.id.itemAddToListButton), hasSibling(withText("2. Hamburgers")))).perform(click());
        //onView(withId(R.id.itemAddToListButton),hasSibling(withText("1. Apples")));
        //onView(allOf(withText("Add this item"),isDescendantOfA(withText("Apples")))).perform(click());
        // onView(withId(R.id.itemAddToListButton)).perform(click());
        onView(withId(R.id.addItemQuantityEditText)).perform(typeText("1"));
        Espresso.pressBack();
        onView(withId(R.id.addItemUnitTypeEditText)).perform(typeText("pounds"));
        Espresso.pressBack();
        onView(withText("ADD ITEM TO LIST")).inRoot(isDialog()).perform(click());
        onView(withId(R.id.isCheckedCheckBox)).perform(click());
        Espresso.pressBack();
        Espresso.pressBack();
        Espresso.pressBack();
        //onView(allOf(withId(R.id.removeButton), hasSibling(withText("ListToTestCheckingItems")))).perform(click());
        onView(withId(R.id.removeButton)).perform(click());
        onView(withText("Delete List")).inRoot(isDialog()).perform(click());
    }

    @Test
    public void J_CheckTestonListBeer(){//deletes an already existing list
        onView(withId(R.id.username)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.createListBtn)).perform(click());
        onView(withId(R.id.createGroceryListEditText)).perform(typeText("ListToTestCheckingItems3"));
        Espresso.pressBack();
        onView(withText("Create List")).inRoot(isDialog()).perform(click());
        onView(withSubstring("ListToTestCheckingItems3")).perform(click());
        onView(withId(R.id.browseItemsButton)).perform(click());
        onView(withSubstring("Beverages")).perform(click());
        onView(allOf(withId(R.id.itemAddToListButton), hasSibling(withText("3. Beer")))).perform(click());
        //onView(withId(R.id.itemAddToListButton),hasSibling(withText("1. Apples")));
        //onView(allOf(withText("Add this item"),isDescendantOfA(withText("Apples")))).perform(click());
        // onView(withId(R.id.itemAddToListButton)).perform(click());
        onView(withId(R.id.addItemQuantityEditText)).perform(typeText("4"));
        Espresso.pressBack();
        onView(withId(R.id.addItemUnitTypeEditText)).perform(typeText("oz"));
        Espresso.pressBack();
        onView(withText("ADD ITEM TO LIST")).inRoot(isDialog()).perform(click());
        onView(withId(R.id.isCheckedCheckBox)).perform(click());
        Espresso.pressBack();
        Espresso.pressBack();
        Espresso.pressBack();
        //onView(allOf(withId(R.id.removeButton), hasSibling(withText("ListToTestCheckingItems")))).perform(click());
        onView(withId(R.id.removeButton)).perform(click());
        onView(withText("Delete List")).inRoot(isDialog()).perform(click());
    }
    @Test
    public void K_BrowseItemSalmon(){//deletes an already existing list
        onView(withId(R.id.username)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.createListBtn)).perform(click());
        onView(withId(R.id.createGroceryListEditText)).perform(typeText("ListToTestBrowsingItems1"));
        Espresso.pressBack();
        onView(withText("Create List")).inRoot(isDialog()).perform(click());
        onView(withSubstring("ListToTestBrowsingItems1")).perform(click());
        onView(withId(R.id.browseItemsButton)).perform(click());
        onView(withSubstring("Seafood")).perform(click());
        onView(allOf(withId(R.id.itemAddToListButton), hasSibling(withText("1. Salmon")))).perform(click());
        //onView(withId(R.id.itemAddToListButton),hasSibling(withText("1. Apples")));
        //onView(allOf(withText("Add this item"),isDescendantOfA(withText("Apples")))).perform(click());
        // onView(withId(R.id.itemAddToListButton)).perform(click());
        onView(withId(R.id.addItemQuantityEditText)).perform(typeText("1"));
        Espresso.pressBack();
        onView(withId(R.id.addItemUnitTypeEditText)).perform(typeText("pound"));
        Espresso.pressBack();
        onView(withText("ADD ITEM TO LIST")).inRoot(isDialog()).perform(click());
        Espresso.pressBack();
        Espresso.pressBack();
        Espresso.pressBack();
        //onView(allOf(withId(R.id.removeButton), hasSibling(withText("ListToTestCheckingItems")))).perform(click());
        onView(withId(R.id.removeButton)).perform(click());
        onView(withText("Delete List")).inRoot(isDialog()).perform(click());
    }
    @Test
    public void K_BrowseItemChips(){//deletes an already existing list
        onView(withId(R.id.username)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.createListBtn)).perform(click());
        onView(withId(R.id.createGroceryListEditText)).perform(typeText("ListToTestBrowsingItems2"));
        Espresso.pressBack();
        onView(withText("Create List")).inRoot(isDialog()).perform(click());
        onView(withSubstring("ListToTestBrowsingItems2")).perform(click());
        onView(withId(R.id.browseItemsButton)).perform(click());
        onView(withSubstring("Snacks")).perform(click());
        onView(allOf(withId(R.id.itemAddToListButton), hasSibling(withText("1. Potato Chips")))).perform(click());
        //onView(withId(R.id.itemAddToListButton),hasSibling(withText("1. Apples")));
        //onView(allOf(withText("Add this item"),isDescendantOfA(withText("Apples")))).perform(click());
        // onView(withId(R.id.itemAddToListButton)).perform(click());
        onView(withId(R.id.addItemQuantityEditText)).perform(typeText("423"));
        Espresso.pressBack();
        onView(withId(R.id.addItemUnitTypeEditText)).perform(typeText("grams"));
        Espresso.pressBack();
        onView(withText("ADD ITEM TO LIST")).inRoot(isDialog()).perform(click());
        Espresso.pressBack();
        Espresso.pressBack();
        Espresso.pressBack();
        //onView(allOf(withId(R.id.removeButton), hasSibling(withText("ListToTestCheckingItems")))).perform(click());
        onView(withId(R.id.removeButton)).perform(click());
        onView(withText("Delete List")).inRoot(isDialog()).perform(click());
    }
    @Test
    public void K_BrowseItemMilk(){//deletes an already existing list
        onView(withId(R.id.username)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.createListBtn)).perform(click());
        onView(withId(R.id.createGroceryListEditText)).perform(typeText("ListToTestBrowsingItems2"));
        Espresso.pressBack();
        onView(withText("Create List")).inRoot(isDialog()).perform(click());
        onView(withSubstring("ListToTestBrowsingItems2")).perform(click());
        onView(withId(R.id.browseItemsButton)).perform(click());
        onView(withSubstring("Dairy")).perform(click());
        onView(allOf(withId(R.id.itemAddToListButton), hasSibling(withText("1. Milk")))).perform(click());
        //onView(withId(R.id.itemAddToListButton),hasSibling(withText("1. Apples")));
        //onView(allOf(withText("Add this item"),isDescendantOfA(withText("Apples")))).perform(click());
        // onView(withId(R.id.itemAddToListButton)).perform(click());
        onView(withId(R.id.addItemQuantityEditText)).perform(typeText("1"));
        Espresso.pressBack();
        onView(withId(R.id.addItemUnitTypeEditText)).perform(typeText("gallon"));
        Espresso.pressBack();
        onView(withText("ADD ITEM TO LIST")).inRoot(isDialog()).perform(click());
        Espresso.pressBack();
        Espresso.pressBack();
        Espresso.pressBack();
        //onView(allOf(withId(R.id.removeButton), hasSibling(withText("ListToTestCheckingItems")))).perform(click());
        onView(withId(R.id.removeButton)).perform(click());
        onView(withText("Delete List")).inRoot(isDialog()).perform(click());
    }
    @Test
    public void K_BrowseItem(){
        onView(withId(R.id.username)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.createListBtn)).perform(click());
        onView(withId(R.id.createGroceryListEditText)).perform(typeText("ListToTestBrowsingItems3"));
        Espresso.pressBack();
        onView(withText("Create List")).inRoot(isDialog()).perform(click());
        onView(withSubstring("ListToTestBrowsingItems3")).perform(click());
        onView(withId(R.id.browseItemsButton)).perform(click());
        onView(withSubstring("Pastries")).perform(click());
        onView(allOf(withId(R.id.itemAddToListButton), hasSibling(withText("1. Cake")))).perform(click());
        onView(withId(R.id.addItemQuantityEditText)).perform(typeText("500"));
        Espresso.pressBack();
        onView(withId(R.id.addItemUnitTypeEditText)).perform(typeText("grams"));
        Espresso.pressBack();
        onView(withText("ADD ITEM TO LIST")).inRoot(isDialog()).perform(click());
        Espresso.pressBack();
        Espresso.pressBack();
        Espresso.pressBack();
        //onView(allOf(withId(R.id.removeButton), hasSibling(withText("ListToTestCheckingItems")))).perform(click());
        onView(withId(R.id.removeButton)).perform(click());
        onView(withText("Delete List")).inRoot(isDialog()).perform(click());
    }
    @Test
    public void L_DeleteItemFromList(){
        onView(withId(R.id.username)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.createListBtn)).perform(click());
        onView(withId(R.id.createGroceryListEditText)).perform(typeText("DeleteItemFromList"));
        Espresso.pressBack();
        onView(withText("Create List")).inRoot(isDialog()).perform(click());
        onView(withSubstring("DeleteItemFromList")).perform(click());
        onView(withId(R.id.browseItemsButton)).perform(click());
        onView(withSubstring("Dairy")).perform(click());
        onView(allOf(withId(R.id.itemAddToListButton), hasSibling(withText("1. Milk")))).perform(click());
        onView(withId(R.id.addItemQuantityEditText)).perform(typeText("1"));
        Espresso.pressBack();
        onView(withId(R.id.addItemUnitTypeEditText)).perform(typeText("gallon"));
        Espresso.pressBack();
        onView(withText("ADD ITEM TO LIST")).inRoot(isDialog()).perform(click());
        onView(withId(R.id.deleteItemButton)).perform(click());
        onView(withText("Delete Item")).inRoot(isDialog()).perform(click());
        Espresso.pressBack();
        Espresso.pressBack();
        Espresso.pressBack();
        //onView(allOf(withId(R.id.removeButton), hasSibling(withText("ListToTestCheckingItems")))).perform(click());
        onView(withId(R.id.removeButton)).perform(click());
        onView(withText("Delete List")).inRoot(isDialog()).perform(click());
    }

    @Test
    public void M_EditItem(){
        onView(withId(R.id.username)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.createListBtn)).perform(click());
        onView(withId(R.id.createGroceryListEditText)).perform(typeText("DeleteItemFromList"));
        Espresso.pressBack();
        onView(withText("Create List")).inRoot(isDialog()).perform(click());
        onView(withSubstring("DeleteItemFromList")).perform(click());
        onView(withId(R.id.browseItemsButton)).perform(click());
        onView(withSubstring("Dairy")).perform(click());
        onView(allOf(withId(R.id.itemAddToListButton), hasSibling(withText("1. Milk")))).perform(click());
        //onView(withId(R.id.itemAddToListButton),hasSibling(withText("1. Apples")));
        //onView(allOf(withText("Add this item"),isDescendantOfA(withText("Apples")))).perform(click());
        // onView(withId(R.id.itemAddToListButton)).perform(click());
        onView(withId(R.id.addItemQuantityEditText)).perform(typeText("1"));
        Espresso.pressBack();
        onView(withId(R.id.addItemUnitTypeEditText)).perform(typeText("gallon"));
        Espresso.pressBack();
        onView(withText("ADD ITEM TO LIST")).inRoot(isDialog()).perform(click());
        onView(withId(R.id.changeItemQuantityAndUnitButton)).perform(click());
        onView(withId(R.id.changeItemQuantityEditText)).perform(typeText("7"));
        Espresso.pressBack();
        onView(withId(R.id.changeItemUnitEditText)).perform(typeText("liter"));
        Espresso.pressBack();
        onView(withText("OK")).inRoot(isDialog()).perform(click());
        Espresso.pressBack();
        Espresso.pressBack();
        Espresso.pressBack();
        //onView(allOf(withId(R.id.removeButton), hasSibling(withText("ListToTestCheckingItems")))).perform(click());
        onView(withId(R.id.removeButton)).perform(click());
        onView(withText("Delete List")).inRoot(isDialog()).perform(click());
    }

    @Test
    public void N_AddingAnItem(){
        onView(withId(R.id.username)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.createListBtn)).perform(click());
        onView(withId(R.id.createGroceryListEditText)).perform(typeText("AddingAnItemTest"));
        Espresso.pressBack();
        onView(withText("Create List")).inRoot(isDialog()).perform(click());
        onView(withSubstring("AddingAnItemTest")).perform(click());
        onView(withId(R.id.addByType)).perform(click());
        onView(withId(R.id.searchItemByNameEditText)).perform(typeText("Be"));
        onView(withId(R.id.searchItemByNameButton)).perform(click());
        onView(allOf(withId(R.id.itemAddToListButton), hasSibling(withText("5. Beer")))).perform(click());
        //onView(withId(R.id.itemAddToListButton),hasSibling(withText("1. Apples")));
        //onView(allOf(withText("Add this item"),isDescendantOfA(withText("Apples")))).perform(click());
        // onView(withId(R.id.itemAddToListButton)).perform(click());
        onView(withId(R.id.addItemQuantityEditText)).perform(typeText("1"));
        Espresso.pressBack();
        onView(withId(R.id.addItemUnitTypeEditText)).perform(typeText("gallon"));
        Espresso.pressBack();
        onView(withText("ADD ITEM TO LIST")).inRoot(isDialog()).perform(click());
        Espresso.pressBack();
        Espresso.pressBack();
        Espresso.pressBack();
        //onView(allOf(withId(R.id.removeButton), hasSibling(withText("ListToTestCheckingItems")))).perform(click());
        onView(withId(R.id.removeButton)).perform(click());
        onView(withText("Delete List")).inRoot(isDialog()).perform(click());
    }
    @Test
    public void O_ClearingAllChecks(){
        onView(withId(R.id.username)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.createListBtn)).perform(click());
        onView(withId(R.id.createGroceryListEditText)).perform(typeText("ListToTestBrowsingItems2"));
        Espresso.pressBack();
        onView(withText("Create List")).inRoot(isDialog()).perform(click());
        onView(withSubstring("ListToTestBrowsingItems2")).perform(click());
        onView(withId(R.id.browseItemsButton)).perform(click());
        onView(withSubstring("Snacks")).perform(click());
        onView(allOf(withId(R.id.itemAddToListButton), hasSibling(withText("1. Potato Chips")))).perform(click());
        //onView(withId(R.id.itemAddToListButton),hasSibling(withText("1. Apples")));
        //onView(allOf(withText("Add this item"),isDescendantOfA(withText("Apples")))).perform(click());
        // onView(withId(R.id.itemAddToListButton)).perform(click());
        onView(withId(R.id.addItemQuantityEditText)).perform(typeText("423"));
        Espresso.pressBack();
        onView(withId(R.id.addItemUnitTypeEditText)).perform(typeText("grams"));
        Espresso.pressBack();
        onView(withText("ADD ITEM TO LIST")).inRoot(isDialog()).perform(click());
        onView((withId(R.id.isCheckedCheckBox))).perform(click());
        onView(withId(R.id.clearAll)).perform(click());
        Espresso.pressBack();
        Espresso.pressBack();
        Espresso.pressBack();
        //onView(allOf(withId(R.id.removeButton), hasSibling(withText("ListToTestCheckingItems")))).perform(click());
        onView(withId(R.id.removeButton)).perform(click());
        onView(withText("Delete List")).inRoot(isDialog()).perform(click());
    }
    @Test
    public void P_AddingANewItemType(){
        onView(withId(R.id.username)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.createListBtn)).perform(click());
        onView(withId(R.id.createGroceryListEditText)).perform(typeText("ListForTestingOtherOptions"));
        Espresso.pressBack();
        onView(withText("Create List")).inRoot(isDialog()).perform(click());
        onView(withSubstring("ListForTestingOtherOptions")).perform(click());
        onView(withId(R.id.otherOptionsAct5)).perform(click());
        onView(withId(R.id.itemNameDB)).perform(typeText("new itemtype"));
        Espresso.pressBack();
        onView(withId(R.id.addingItemTypeDB)).perform(click());
        Espresso.pressBack();
        onView(withId(R.id.browseItemsButton)).perform(click());
        onView(withSubstring("new itemtype")).check(matches(isDisplayed()));
        Espresso.pressBack();
        Espresso.pressBack();
        onView(withId(R.id.removeButton)).perform(click());
        onView(withText("Delete List")).inRoot(isDialog()).perform(click());
    }

    @Test
    public void P_AddingANewItemType2(){
        onView(withId(R.id.username)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.createListBtn)).perform(click());
        onView(withId(R.id.createGroceryListEditText)).perform(typeText("ListForTestingOtherOptions2"));
        Espresso.pressBack();
        onView(withText("Create List")).inRoot(isDialog()).perform(click());
        onView(withSubstring("ListForTestingOtherOptions2")).perform(click());
        onView(withId(R.id.otherOptionsAct5)).perform(click());
        onView(withId(R.id.itemNameDB)).perform(typeText("new itemtype2"));
        Espresso.pressBack();
        onView(withId(R.id.addingItemTypeDB)).perform(click());
        Espresso.pressBack();
        onView(withId(R.id.browseItemsButton)).perform(click());
        onView(withSubstring("new itemtype2")).check(matches(isDisplayed()));
        Espresso.pressBack();
        Espresso.pressBack();
        onView(withId(R.id.removeButton)).perform(click());
        onView(withText("Delete List")).inRoot(isDialog()).perform(click());
    }
}
