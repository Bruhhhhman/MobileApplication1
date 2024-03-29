package ru.mirea.udalovna.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.annotation.NonNull
import androidx.fragment.app.DialogFragment

class AlertDialogFragment : DialogFragment() {
    @NonNull
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("Здравствуй МИРЭА!")
            .setMessage("Успех близок?")
            .setPositiveButton("Надпись1", DialogInterface.OnClickListener { dialog, id ->
                (activity as? MainActivity)?.onOkClicked()
            })
            .setNeutralButton("Надпись2", DialogInterface.OnClickListener { dialog, id ->
                (activity as? MainActivity)?.onNeutralClicked()
            })
            .setNegativeButton("Надпись3", DialogInterface.OnClickListener { dialog, id ->
                (activity as? MainActivity)?.onCancelClicked()
            })
        return builder.create()
    }
}