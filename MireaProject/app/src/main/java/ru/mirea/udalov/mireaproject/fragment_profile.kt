package ru.mirea.udalov.mireaproject

import android.content.SharedPreferences
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.mirea.udalov.mireaproject.databinding.FragmentProfileBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_profile : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireActivity().getSharedPreferences("profile_prefs", Context.MODE_PRIVATE)

        loadSavedData()

        binding.btnSave.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val phoneNumber = binding.etPhoneNumber.text.toString()

            with(sharedPreferences.edit()) {
                putString("email", email)
                putString("phone_number", phoneNumber)
                apply()
            }

            showSavedData(email, phoneNumber)
        }
    }

    private fun loadSavedData() {
        val savedEmail = sharedPreferences.getString("email", "")
        val savedPhoneNumber = sharedPreferences.getString("phone_number", "")

        binding.etEmail.setText(savedEmail)
        binding.etPhoneNumber.setText(savedPhoneNumber)

        if (savedEmail?.isNotEmpty() == true && savedPhoneNumber?.isNotEmpty() == true) {
            showSavedData(savedEmail, savedPhoneNumber)
        }
    }

    private fun showSavedData(email: String, phoneNumber: String) {
        binding.tvSavedEmail.apply {
            visibility = View.VISIBLE
            text = "Сохранённый email: $email"
        }
        binding.tvSavedPhoneNumber.apply {
            visibility = View.VISIBLE
            text = "Сохранённый номер телефона: $phoneNumber"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}