package com.example.nasaimages.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.nasaimages.R
import com.example.nasaimages.network.rx.ResponseState
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.display_image_fragment.*
import java.util.*
import javax.inject.Inject


open class DisplayImageFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(DisplayImageViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.display_image_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_set_date.setOnClickListener {
            showDatePicker()
        }
        viewModel.getNasaImage.observe(viewLifecycleOwner, {
            when (it) {

                is ResponseState.Loading -> {
                    progress_bar.visibility = View.VISIBLE
                }
                is ResponseState.Success -> {
                    progress_bar.visibility = View.GONE
                    val nasaImage = it.response.nasaImage
                    this.context?.let { it1 ->
                        Glide.with(it1)
                            .load(nasaImage.url)
                            .placeholder(R.drawable.ic_nasa_icon)
                            .error(R.drawable.ic_nasa_icon)
                            .into(nasa_image)
                    }
                    txt_display_date.text = nasaImage.date
                    txt_explanation.text = nasaImage.explanation
                    txt_title.text = nasaImage.title
                }
                is ResponseState.Error -> {
                    progress_bar.visibility = View.GONE
                    Toast.makeText(activity, it.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    private fun showDatePicker() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        val mDatePicker = this.context?.let {
            DatePickerDialog(it, { view, year, monthOfYear, dayOfMonth ->
                val actualMonth = monthOfYear + 1

                val month = if (actualMonth < 10) {
                    "0$actualMonth"
                } else {
                    actualMonth.toString()
                }
                val day = if (dayOfMonth < 10) {
                    "0$dayOfMonth"
                } else {
                    dayOfMonth.toString()
                }

                viewModel.fetchNewsList("$year-$month-$day")
            }, year, month, day)
        }
        mDatePicker?.datePicker?.maxDate = System.currentTimeMillis()
        mDatePicker?.show()
    }


}
