package com.media.profilecheck

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.media.profilecheck.adapter.UserAdapter
import com.media.profilecheck.databinding.ActivityMainBinding
import com.media.profilecheck.models.UsersResult
import com.media.profilecheck.repository.NetworkResult
import com.media.profilecheck.repository.UserData
import com.media.profilecheck.room.UserDatabase
import com.media.profilecheck.utils.InternetConnection
import com.media.profilecheck.viewmodel.MainViewModel
import com.media.profilecheck.viewmodel.MainViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private lateinit var binding: ActivityMainBinding
    private lateinit var mBottomSheetBehavior: BottomSheetBehavior<NestedScrollView>
    private lateinit var connectionData: InternetConnection
    private lateinit var userDatabase: UserDatabase

    private val userAdapter by lazy {
        UserAdapter()
    }

    private var usersList: ArrayList<UsersResult> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (application as UserApplication).applicationComponent.inject(this)
        bindUiViews()
    }


    private fun bindUiViews() {

        connectionData = InternetConnection(this)
        userDatabase = UserDatabase.getDbInstance(this)

        viewModel = ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]

        binding.progressBar.visibility = View.VISIBLE
        val bottomSheet = binding.bottomSheetCustomNumber
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        mBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        viewModel.getUsers(40)
        getData()
        setFilters()
        handleSearchCloseButton()
        bottomSheetBehaviour()
        handleInternetConnection()

        binding.btnSearch.setOnClickListener(this)
        binding.ivRemoveFilter.setOnClickListener(this)
        binding.tvCustomText.setOnClickListener(this)
        binding.btnSubmit.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnSearch -> setSearch()
            R.id.ivRemoveFilter -> {
                val radioButton = binding.rgGender.findViewById<RadioButton>(binding.rgGender.checkedRadioButtonId)
                radioButton.isChecked = !radioButton.isChecked
                binding.ivRemoveFilter.visibility = View.GONE
                getData()
            }
            R.id.tvCustomText -> {
                mBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
            R.id.btnSubmit -> {
                connectionData.observe(this) {
                    if (it == true) {
                        val customText = binding.etNumber.text?.trim().toString()

                        if (customText.isNotEmpty()) {
                            viewModel.getUsers(customText.toInt())
                            binding.etNumber.text = null
                        }
                        mBottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                    }
                }
            }
        }
    }

    //Getting data from view model
    private fun getData() {
        viewModel.users.observe(this) {

            when (it) {
                is NetworkResult.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.tvUsersCount.text = resources.getString(R.string.users) + ": ${it.data.results?.size}"
                    it.data.results?.let { it1 -> rendersData(it1) }
                    it.data.results?.let { it1 -> saveData(it1) }
                }
                is NetworkResult.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Log.e("Data", it.errorCode.toString())
                }
                is NetworkResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    //Data set in adapter and recycler view
    private fun rendersData(results: List<UsersResult>) {
        usersList = results as ArrayList<UsersResult>
        userAdapter.setList(results, this)
        binding.rvUsersList.adapter = userAdapter

    }

    //Filter the Data
    private fun setFilters() {

        binding.rgGender.setOnCheckedChangeListener { radioGroup, i ->
            val radioButton = binding.rgGender.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
            val filterData: List<UsersResult> = usersList.filter {
                binding.ivRemoveFilter.visibility = View.VISIBLE
                it.gender == radioButton.tag.toString()
            }
            userAdapter.setList(filterData as ArrayList<UsersResult>, this)
        }

    }

    //Handle Searching
    private fun setSearch() {
        val searchText = binding.etSearchText.query.trim().toString()
        val searchResult = userAdapter.getFilter(searchText)
        if (!searchResult) {
            binding.rvUsersList.visibility = View.GONE
            binding.tvNoDataFound.visibility = View.VISIBLE
        }
    }

    //Handle Search Close Button
    private fun handleSearchCloseButton() {
        val clearButton: ImageView = binding.etSearchText.findViewById(androidx.appcompat.R.id.search_close_btn)

        clearButton.setOnClickListener {
            if (binding.etSearchText.query.isEmpty()) {
                binding.etSearchText.isIconified = true
            } else {
                binding.rvUsersList.visibility = View.VISIBLE
                binding.tvNoDataFound.visibility = View.GONE
                getData()
                binding.etSearchText.setQuery("", false)
            }
        }
    }

    //Manage the Bottom Sheet Behaviour
    private fun bottomSheetBehaviour() {

        mBottomSheetBehavior.setBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_COLLAPSED -> binding.viewBackground.visibility =
                        View.GONE
                    BottomSheetBehavior.STATE_HIDDEN -> binding.viewBackground.visibility =
                        View.GONE
                    BottomSheetBehavior.STATE_DRAGGING -> binding.viewBackground.visibility =
                        View.VISIBLE
                    BottomSheetBehavior.STATE_EXPANDED -> binding.viewBackground.visibility =
                        View.VISIBLE
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> binding.viewBackground.visibility =
                        View.VISIBLE
                    BottomSheetBehavior.STATE_SETTLING -> binding.viewBackground.visibility =
                        View.VISIBLE
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })

    }

    //Handles the internet connection in realtime
    private fun handleInternetConnection() {
        /*CoroutineScope(Dispatchers.IO).launch {
            connectionData.observe(lifecycle as LifecycleOwner) {
                if (it == false) {
                    getDataRoomDb()
                    binding.tvNoInternet.visibility = View.VISIBLE
                } else {
                    binding.tvNoInternet.visibility = View.GONE
                }
            }

        }*/

    }

    private fun saveData(userList: List<UsersResult>) {

        CoroutineScope(Dispatchers.IO).launch {
            val userData = UserData(userList)
            userDatabase.userDao.insertData(userData)
        }

    }

    private fun getDataRoomDb() {

        userDatabase.userDao.getUserData().observe(this) {
            Log.e("Data", ""+it.size)
            //binding.tvUsersCount.text = resources.getString(R.string.users) + ": ${it.size}"
            //it.forEach {
            //    it.results?.let { it1 -> rendersData(it1) }
            //}
        }

    }


}