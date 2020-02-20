package com.example.addressbook;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.TextUtils;
import android.text.format.Time;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	DBAdapter myDb;
	EditText etName;
	EditText etStreetAddress;
	EditText etCity;
	EditText etState;
	EditText etZip;
	Button btAdd;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        etName = (EditText)findViewById(R.id.editTextName);
        etStreetAddress = (EditText)findViewById(R.id.editTextStreetAddress);
        etCity = (EditText)findViewById(R.id.editTextCity);
        etState = (EditText)findViewById(R.id.editTextState);
        etZip = (EditText)findViewById(R.id.editTextZip);
        btAdd = (Button)findViewById(R.id.buttonAdd);
        openDb();
        populateListView();
        listViewItemClick();
        listViewItemLongClick();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

    private void openDb()
    {
    	myDb = new DBAdapter(this);
    	myDb.open();
    }
    
    public void onClickAddAddress(View v)
    {
    	if(!TextUtils.isEmpty(etName.getText().toString()))
    	{
    		int counter = 0;
    		
    		if (TextUtils.isEmpty( etName.getText().toString() ))
    		{
    			counter ++;
    		}
    		String street_address = etStreetAddress.getText().toString();
    		if (TextUtils.isEmpty( etStreetAddress.getText().toString() ))
    		{
    			counter += 2;
    		}
    		String city = etCity.getText().toString();
    		if (TextUtils.isEmpty( etCity.getText().toString() ))
    		{
    			counter += 4;
    		}
    		String state = etState.getText().toString();
    		if (TextUtils.isEmpty( etState.getText().toString() ))
    		{
    			counter += 8;
    		}
    		String zip = etZip.getText().toString();
    		if (TextUtils.isEmpty( etZip.getText().toString() ))
    		{
    			counter += 16;
    		}
    		if(counter != 0)
    		{
    			switch (counter) 
    		{
    			case 1:
    				String message1 = "Name is blank";
					Toast.makeText(MainActivity.this, message1, Toast.LENGTH_LONG).show();
    				break;
    			case 2:
    				String message2 = "Street address is blank";
					Toast.makeText(MainActivity.this, message2, Toast.LENGTH_LONG).show();
					break;
    			case 3: //Combo
    				String message3 = "Name and street address are blank";
					Toast.makeText(MainActivity.this, message3, Toast.LENGTH_LONG).show();
					break;
    			case 4:
    				String message4 = "City is blank";
					Toast.makeText(MainActivity.this, message4, Toast.LENGTH_LONG).show();
					break;
    			case 5: //Combo
    				String message5 = "City and name are blank";
					Toast.makeText(MainActivity.this, message5, Toast.LENGTH_LONG).show();
					break;
				case 6: //Combo
					String message6 = "City and Street address are blank";
					Toast.makeText(MainActivity.this, message6, Toast.LENGTH_LONG).show();
					break;
				case 7://Combo
					String message7 = "City, street address, and name are blank";
					Toast.makeText(MainActivity.this, message7, Toast.LENGTH_LONG).show();
					break;
				case 8:
					String message8 = "State is blank";
					Toast.makeText(MainActivity.this, message8, Toast.LENGTH_LONG).show();
					break;
				case 9: //Combo
					String message9 = "State and name are blank";
					Toast.makeText(MainActivity.this, message9, Toast.LENGTH_LONG).show();
					break;
				case 10: //Combo
					String message = "State and street address are blank";
					Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
					break;
				case 11: //Combo
					String message11 = "State, street address, and name are blank";
					Toast.makeText(MainActivity.this, message11, Toast.LENGTH_LONG).show();
					break;
				case 12:
					String message12 = "State and city are blank";
					Toast.makeText(MainActivity.this, message12, Toast.LENGTH_LONG).show();
					break;
				case 13:
					String message13 = "State, city, and name are blank";
					Toast.makeText(MainActivity.this, message13, Toast.LENGTH_LONG).show();
					break;
				case 14:
					String message14 = "State, city, and street address are blank";
					Toast.makeText(MainActivity.this, message14, Toast.LENGTH_LONG).show();
					break;
				case 15:
					String message15 = "State, city, street address and name are blank";
					Toast.makeText(MainActivity.this, message15, Toast.LENGTH_LONG).show();
					break;
				case 16:
					String message16 = "Zip is blank";
					Toast.makeText(MainActivity.this, message16, Toast.LENGTH_LONG).show();
					break;
				case 17:
					String message17 = "Zip and name are blank";
					Toast.makeText(MainActivity.this, message17, Toast.LENGTH_LONG).show();
					break;
				case 18:
					String message18 = "Zip and street address are blank";
					Toast.makeText(MainActivity.this, message18, Toast.LENGTH_LONG).show();
					break;
				case 19:
					String message19 = "Zip, street address, and name are blank";
					Toast.makeText(MainActivity.this, message19, Toast.LENGTH_LONG).show();
					break;
				case 20:
					String message20 = "Zip and city are blank";
					Toast.makeText(MainActivity.this, message20, Toast.LENGTH_LONG).show();
					break;
				case 21:
					String message21 = "Zip, city, and name are blank";
					Toast.makeText(MainActivity.this, message21, Toast.LENGTH_LONG).show();
					break;
				case 22:
					String message22 = "Zip, city, and street address are blank";
					Toast.makeText(MainActivity.this, message22, Toast.LENGTH_LONG).show();
					break;
				case 23:
					String message23 = "Zip, city, street address, and name are blank";
					Toast.makeText(MainActivity.this, message23, Toast.LENGTH_LONG).show();
					break;
				case 31:
					String message31 = "No information was entered";
					Toast.makeText(MainActivity.this, message31, Toast.LENGTH_LONG).show();
					break;
    			default:
    				break;
    	}
    		}
    		myDb.insertRow(etName.getText().toString(), etStreetAddress.getText().toString(), etCity.getText().toString(), etState.getText().toString(), etZip.getText().toString());
    	}
    	populateListView();
    }
    
    private void populateListView()
    {
    	Cursor cursor = myDb.getAllRows();
    	String[] fromFieldNames = new String[] {DBAdapter.KEY_NAME, DBAdapter.KEY_STREET_ADDRESS, DBAdapter.KEY_CITY, DBAdapter.KEY_STATE, DBAdapter.KEY_ZIP};
    	int[] toViewIDs = new int[] {R.id.textViewName, R.id.textViewStreetAddress, R.id.textViewCity, R.id.textViewState, R.id.textViewZip};
    	SimpleCursorAdapter myCursorAdapter;
    	myCursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.item_layout, cursor, fromFieldNames, toViewIDs, 0);
    	ListView myList = (ListView)findViewById(R.id.listViewAddresses);
    	myList.setAdapter(myCursorAdapter);
    }
    
    private void updateAddress(long id)
    {
    	int counter = 0;
    	
    	Cursor cursor = myDb.getRow(id);
    	if( cursor.moveToFirst() ){
    		String name = etName.getText().toString();
    		if (TextUtils.isEmpty( etName.getText().toString() ))
    		{
    			counter ++;
    		}
    		String street_address = etStreetAddress.getText().toString();
    		if (TextUtils.isEmpty( etStreetAddress.getText().toString() ))
    		{
    			counter += 2;
    		}
    		String city = etCity.getText().toString();
    		if (TextUtils.isEmpty( etCity.getText().toString() ))
    		{
    			counter += 4;
    		}
    		String state = etState.getText().toString();
    		if (TextUtils.isEmpty( etState.getText().toString() ))
    		{
    			counter += 8;
    		}
    		String zip = etZip.getText().toString();
    		if (TextUtils.isEmpty( etZip.getText().toString() ))
    		{
    			counter += 16;
    		}
    		myDb.updateRow(id, name, street_address, city, state, zip);
    		switch (counter) 
    		{
    			case 0: 
    				break;
    			case 1:
    				String message1 = "Name is blank";
					Toast.makeText(MainActivity.this, message1, Toast.LENGTH_LONG).show();
    				break;
    			case 2:
    				String message2 = "Street address is blank";
					Toast.makeText(MainActivity.this, message2, Toast.LENGTH_LONG).show();
					break;
    			case 3: //Combo
    				String message3 = "Name and street address are blank";
					Toast.makeText(MainActivity.this, message3, Toast.LENGTH_LONG).show();
					break;
    			case 4:
    				String message4 = "City is blank";
					Toast.makeText(MainActivity.this, message4, Toast.LENGTH_LONG).show();
					break;
    			case 5: //Combo
    				String message5 = "City and name are blank";
					Toast.makeText(MainActivity.this, message5, Toast.LENGTH_LONG).show();
					break;
				case 6: //Combo
					String message6 = "City and Street address are blank";
					Toast.makeText(MainActivity.this, message6, Toast.LENGTH_LONG).show();
					break;
				case 7://Combo
					String message7 = "City, street address, and name are blank";
					Toast.makeText(MainActivity.this, message7, Toast.LENGTH_LONG).show();
					break;
				case 8:
					String message8 = "State is blank";
					Toast.makeText(MainActivity.this, message8, Toast.LENGTH_LONG).show();
					break;
				case 9: //Combo
					String message9 = "State and name are blank";
					Toast.makeText(MainActivity.this, message9, Toast.LENGTH_LONG).show();
					break;
				case 10: //Combo
					String message = "State and street address are blank";
					Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
					break;
				case 11: //Combo
					String message11 = "State, street address, and name are blank";
					Toast.makeText(MainActivity.this, message11, Toast.LENGTH_LONG).show();
					break;
				case 12:
					String message12 = "State and city are blank";
					Toast.makeText(MainActivity.this, message12, Toast.LENGTH_LONG).show();
					break;
				case 13:
					String message13 = "State, city, and name are blank";
					Toast.makeText(MainActivity.this, message13, Toast.LENGTH_LONG).show();
					break;
				case 14:
					String message14 = "State, city, and street address are blank";
					Toast.makeText(MainActivity.this, message14, Toast.LENGTH_LONG).show();
					break;
				case 15:
					String message15 = "State, city, street address and name are blank";
					Toast.makeText(MainActivity.this, message15, Toast.LENGTH_LONG).show();
					break;
				case 16:
					String message16 = "Zip is blank";
					Toast.makeText(MainActivity.this, message16, Toast.LENGTH_LONG).show();
					break;
				case 17:
					String message17 = "Zip and name are blank";
					Toast.makeText(MainActivity.this, message17, Toast.LENGTH_LONG).show();
					break;
				case 18:
					String message18 = "Zip and street address are blank";
					Toast.makeText(MainActivity.this, message18, Toast.LENGTH_LONG).show();
					break;
				case 19:
					String message19 = "Zip, street address, and name are blank";
					Toast.makeText(MainActivity.this, message19, Toast.LENGTH_LONG).show();
					break;
				case 20:
					String message20 = "Zip and city are blank";
					Toast.makeText(MainActivity.this, message20, Toast.LENGTH_LONG).show();
					break;
				case 21:
					String message21 = "Zip, city, and name are blank";
					Toast.makeText(MainActivity.this, message21, Toast.LENGTH_LONG).show();
					break;
				case 22:
					String message22 = "Zip, city, and street address are blank";
					Toast.makeText(MainActivity.this, message22, Toast.LENGTH_LONG).show();
					break;
				case 23:
					String message23 = "Zip, city, street address, and name are blank";
					Toast.makeText(MainActivity.this, message23, Toast.LENGTH_LONG).show();
					break;
				case 31:
					String message31 = "No information was entered";
					Toast.makeText(MainActivity.this, message31, Toast.LENGTH_LONG).show();
					break;
    			default:
    				break;
    		}
    	}
    	cursor.close();
    }
    private void listViewItemClick()
    {
    	ListView myList = (ListView)findViewById(R.id.listViewAddresses);
    	myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				updateAddress(id);
				populateListView();
				if ((!TextUtils.isEmpty(etName.getText().toString())) && (!TextUtils.isEmpty(etStreetAddress.getText().toString())) && (!TextUtils.isEmpty(etCity.getText().toString())) && (!TextUtils.isEmpty(etState.getText().toString())) && (!TextUtils.isEmpty(etZip.getText().toString())))
				{
					String message = "Updated!";
					Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
				}
			}
		});
    }
    
    public void listViewItemLongClick()
    {
    	ListView myList = (ListView)findViewById(R.id.listViewAddresses);
    	myList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				myDb.deleteRow(id);
				populateListView();
				return false;
			}
		});
    }
    
    public void displayToast(long id) //**** maybe make one for update one for delete
    {
    	Cursor cursor = myDb.getRow(id);
    	if( cursor.moveToFirst() )
    	{
    		long idDB = cursor.getLong(DBAdapter.COL_ROWID);
    		String name = cursor.getString(DBAdapter.COL_NAME);
    		String street_address = cursor.getString(DBAdapter.COL_STREET_ADDRESS);
    		String city = cursor.getString(DBAdapter.COL_CITY);
    		String state = cursor.getString(DBAdapter.COL_STATE);
    		String zip = cursor.getString(DBAdapter.COL_ZIP);
    		
    		String message = "ID: "+idDB+"\n"+"Name: "+name;
    		Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
    	}
    	cursor.close();
    }
    
    protected void onDestroy()
    {
    	super.onDestroy();
    	closeDB();
    }
    
    private void closeDB()
    {
    	myDb.close();
    }
}
