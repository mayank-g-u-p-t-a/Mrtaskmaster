package com.example.servicebook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.servicebook.databinding.FragmentOrderfragmentBinding

class orderfragment : Fragment() {

    private lateinit var binding:FragmentOrderfragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentOrderfragmentBinding.inflate(inflater,container,false)
        return binding.root
    }


}