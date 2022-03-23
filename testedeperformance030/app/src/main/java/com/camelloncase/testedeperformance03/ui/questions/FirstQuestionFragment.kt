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
import camell.oncase.investorprofileanalyzer.databinding.FragmentFirstQuestionBinding
import camell.oncase.investorprofileanalyzer.viewmodel.InvestorProfileViewModel

class FirstQuestionFragment : Fragment() {

    private var _binding: FragmentFirstQuestionBinding? = null

    private val binding get() = _binding!!

    private val sharedViewModel: InvestorProfileViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstQuestionBinding.inflate(inflater, container, false)

        val view = binding.root

        goToNextScreen(view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            firstQuestionFragment = this@FirstQuestionFragment
        }
    }

    private fun goToNextScreen(view: View) {

        binding.firstQuestionNextButton.setOnClickListener{

            val selectedOption: Int = binding.firstQuestionRadioGroup.checkedRadioButtonId
            val point: LiveData<Int> = sharedViewModel.questionPunctuation

            if (selectedOption == -1) {
                Toast.makeText(context, getString(R.string.unselected_radio_button), Toast.LENGTH_SHORT).show()
            } else {
                sharedViewModel.setProfilePunctuation(point)

                Navigation.findNavController(view).navigate(R.id.action_firstQuestionFragment_to_secondQuestionFragment)
            }
        }

    }

}