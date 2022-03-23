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
import camell.oncase.investorprofileanalyzer.databinding.FragmentFifthQuestionBinding
import camell.oncase.investorprofileanalyzer.viewmodel.InvestorProfileViewModel

class FifthQuestionFragment : Fragment() {
    private var _binding: FragmentFifthQuestionBinding? = null

    private val binding get() = _binding!!

    private val sharedViewModel: InvestorProfileViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFifthQuestionBinding.inflate(inflater, container, false)

        val view = binding.root

        goToNextScreen(view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            fifthQuestionFragment = this@FifthQuestionFragment
        }
    }

    private fun goToNextScreen(view: View) {

        binding.fifthQuestionNextButton.setOnClickListener{

            val selectedOption: Int = binding.fifthQuestionRadioGroup.checkedRadioButtonId
            val point: LiveData<Int> = sharedViewModel.questionPunctuation

            if (selectedOption == -1) {
                Toast.makeText(context, getString(R.string.unselected_radio_button), Toast.LENGTH_SHORT).show()
            } else {
                sharedViewModel.sumProfilePunctuation(point)
                Navigation.findNavController(view).navigate(R.id.action_fifthQuestionFragment_to_sixthQuestionFragment)
            }
        }

    }
}