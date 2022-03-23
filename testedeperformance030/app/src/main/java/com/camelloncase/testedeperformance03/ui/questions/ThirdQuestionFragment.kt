package com.camelloncase.testedeperformance03.ui.questions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.navigation.Navigation
import camell.oncase.investorprofileanalyzer.R
import camell.oncase.investorprofileanalyzer.databinding.FragmentThirdQuestionBinding
import camell.oncase.investorprofileanalyzer.viewmodel.InvestorProfileViewModel

class ThirdQuestionFragment : Fragment() {
    private var _binding: FragmentThirdQuestionBinding? = null

    private val binding get() = _binding!!

    private val sharedViewModel: InvestorProfileViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThirdQuestionBinding.inflate(inflater, container, false)

        val view = binding.root

        goToNextScreen(view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            thirdQuestionFragment = this@ThirdQuestionFragment
        }
    }

    private fun goToNextScreen(view: View) {

        binding.thirdQuestionNextButton.setOnClickListener{

            val selectedOption: Int = binding.thirdQuestionRadioGroup.checkedRadioButtonId
            val point: LiveData<Int> = sharedViewModel.questionPunctuation

            if (selectedOption == -1) {
                Toast.makeText(context, getString(R.string.unselected_radio_button), Toast.LENGTH_SHORT).show()
            } else {
                sharedViewModel.sumProfilePunctuation(point)
                Navigation.findNavController(view).navigate(R.id.action_thirdQuestionFragment_to_fourthQuestionFragment)
            }
        }

    }
}