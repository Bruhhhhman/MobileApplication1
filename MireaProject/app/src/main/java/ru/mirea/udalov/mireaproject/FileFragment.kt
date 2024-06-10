package ru.mirea.udalov.mireaproject

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.mirea.udalov.mireaproject.databinding.FragmentFileBinding
import java.io.FileOutputStream

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FileFragment : Fragment() {

    private var _binding: FragmentFileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fab: FloatingActionButton = binding.fabAddRecord
        fab.setOnClickListener {
            showAddFileDialog()
        }
    }

    private fun showAddFileDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Добавить запись")
        builder.setMessage("Введите текст записи")

        val input = EditText(requireContext())
        builder.setView(input)

        builder.setNeutralButton("Применить операцию") { dialog, _ ->
            val text = input.text.toString()
            showOperationOptionsDialog(text)
            dialog.dismiss()
        }

        builder.setPositiveButton("Сохранить") { dialog, _ ->
            val text = input.text.toString()
            saveToFile(text)
            dialog.dismiss()
        }

        builder.setNegativeButton("Отмена") { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }
    private fun showOperationOptionsDialog(text: String) {
        val options = arrayOf("Изменить запись")

        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Выберите операцию")
        builder.setItems(options) { dialog, which ->
            when (which) {
                0 -> convertFormat(text)
            }
            dialog.dismiss()
        }

        builder.setNegativeButton("Отмена") { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }
    private fun convertFormat(text: String) {
        val encryptedText = text.replace("a", "b")
        saveToFile(encryptedText)
    }


    private fun saveToFile(text: String) {
        val filename = "operation_file.txt"
        val fileContents = text.toByteArray()
        val outputStream: FileOutputStream

        try {
            outputStream = requireContext().openFileOutput(filename, Context.MODE_PRIVATE)
            outputStream.write(fileContents)
            outputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}