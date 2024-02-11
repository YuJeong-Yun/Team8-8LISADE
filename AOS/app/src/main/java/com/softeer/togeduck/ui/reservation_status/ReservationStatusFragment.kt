package com.softeer.togeduck.ui.reservation_status

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.softeer.togeduck.R
import com.softeer.togeduck.databinding.FragmentReservationStatusBinding
import com.softeer.togeduck.data.model.ReservationStatusModel
import com.softeer.togeduck.utils.ItemClick
import java.text.DecimalFormat

class ReservationStatusFragment : Fragment() {
    private var _binding: FragmentReservationStatusBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ReservationStatusAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReservationStatusBinding.inflate(inflater, container, false)

        init()

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun init() {
        adapter = ReservationStatusAdapter(getTestData())
        val rvList = binding.rvReservationStatusList

        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        rvList.addItemDecoration(dividerItemDecoration)
        rvList.adapter = adapter

        adapter.itemClick = object : ItemClick {
            override fun onClick(view: View, position: Int) {
                findNavController().navigate(R.id.action_menuReserveList_to_reservationStatusDetailActivity)
            }
        }
    }

    //////////////// 백엔드 API 연동시 수정 필요 /////////////////
    private fun getTestData(): MutableList<ReservationStatusModel> {
        val testList = mutableListOf<ReservationStatusModel>()

        val dec = DecimalFormat("#,###")

        val testData = ReservationStatusModel(
            "[서울] Exo 콘서트",
            "https://i.namu.wiki/i/sqi3rhs8DtElCCknpMgPgJoTwalucUg506J0v4c6XnTD7Lq_0v3B4vnkw2-LO8iEkksXRdTyLoPb4jnt58IZkzfLOpuJhYTUVVh9x7jlBRezOUWqB-r5m6EOSyecZ3v159XfGjUb94NTckJXr0gJ4A.webp",
            "2024.02.05",
            "14:00",
            "잠실종합운동장",
            30000,
            dec.format(30000),
            true,
            "모집중(4/6)"
        )
        for (i in 0..7) {
            testList.add(testData)
        }
        return testList
    }
    //////////////// 백엔드 API 연동시 수정 필요 /////////////////
}