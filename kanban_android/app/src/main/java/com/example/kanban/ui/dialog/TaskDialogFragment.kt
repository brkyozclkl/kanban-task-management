package com.example.kanban.ui.dialog

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.kanban.R
import com.example.kanban.data.model.Task
import com.example.kanban.databinding.DialogTaskBinding
import com.example.kanban.ui.viewmodel.TaskViewModel
import com.google.android.material.chip.Chip
import java.text.SimpleDateFormat
import java.util.*

class TaskDialogFragment : DialogFragment() {

    private var _binding: DialogTaskBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TaskViewModel by viewModels()
    private var task: Task? = null
    private var dueDate: Date? = null
    private val selectedTags = mutableSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialog)
        task = arguments?.getParcelable(ARG_TASK)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupListeners()
        loadTask()
    }

    private fun setupViews() {
        // Öncelik spinner'ını ayarla
        val priorities = Task.Priority.values().map { it.name }
        val priorityAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            priorities
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        binding.prioritySpinner.adapter = priorityAdapter

        // Durum spinner'ını ayarla
        val statuses = Task.TaskStatus.values().map { it.name }
        val statusAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            statuses
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        binding.statusSpinner.adapter = statusAdapter

        // Mevcut etiketleri yükle
        viewModel.getAllTags().observe(viewLifecycleOwner) { tags ->
            binding.tagGroup.removeAllViews()
            tags.forEach { tag ->
                addTagChip(tag)
            }
        }
    }

    private fun setupListeners() {
        binding.apply {
            // Başlık değişikliğini dinle
            titleEditText.addTextChangedListener {
                saveButton.isEnabled = !it.isNullOrBlank()
            }

            // Tarih seçiciyi ayarla
            dueDateButton.setOnClickListener {
                showDatePicker()
            }

            // Kaydet butonunu ayarla
            saveButton.setOnClickListener {
                saveTask()
            }

            // İptal butonunu ayarla
            cancelButton.setOnClickListener {
                dismiss()
            }

            // Yeni etiket ekleme
            addTagButton.setOnClickListener {
                val newTag = tagEditText.text.toString().trim()
                if (newTag.isNotEmpty()) {
                    selectedTags.add(newTag)
                    addTagChip(newTag)
                    tagEditText.text?.clear()
                }
            }
        }
    }

    private fun loadTask() {
        task?.let { existingTask ->
            binding.apply {
                titleEditText.setText(existingTask.name)
                descriptionEditText.setText(existingTask.description)
                prioritySpinner.setSelection(existingTask.priority.ordinal)
                statusSpinner.setSelection(existingTask.status.ordinal)
                dueDate = existingTask.dueDate
                updateDueDateButton()
                selectedTags.addAll(existingTask.tags)
                existingTask.tags.forEach { tag ->
                    addTagChip(tag)
                }
                sharedSwitch.isChecked = existingTask.isShared
            }
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        dueDate?.let { calendar.time = it }

        DatePickerDialog(
            requireContext(),
            { _, year, month, day ->
                calendar.set(year, month, day)
                dueDate = calendar.time
                updateDueDateButton()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun updateDueDateButton() {
        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        binding.dueDateButton.text = dueDate?.let { dateFormat.format(it) }
            ?: getString(R.string.select_due_date)
    }

    private fun addTagChip(tag: String) {
        val chip = Chip(requireContext()).apply {
            text = tag
            isCloseIconVisible = true
            setOnCloseIconClickListener {
                selectedTags.remove(tag)
                binding.tagGroup.removeView(this)
            }
        }
        binding.tagGroup.addView(chip)
    }

    private fun saveTask() {
        val title = binding.titleEditText.text.toString().trim()
        val description = binding.descriptionEditText.text.toString().trim()
        val priority = Task.Priority.values()[binding.prioritySpinner.selectedItemPosition]
        val status = Task.TaskStatus.values()[binding.statusSpinner.selectedItemPosition]
        val isShared = binding.sharedSwitch.isChecked

        val newTask = task?.copy(
            name = title,
            description = description,
            priority = priority,
            status = status,
            dueDate = dueDate,
            tags = selectedTags.toList(),
            isShared = isShared,
            updatedAt = Date()
        ) ?: Task(
            name = title,
            description = description,
            priority = priority,
            status = status,
            dueDate = dueDate,
            tags = selectedTags.toList(),
            isShared = isShared,
            createdAt = Date(),
            updatedAt = Date()
        )

        if (task == null) {
            viewModel.insertTask(newTask)
        } else {
            viewModel.updateTask(newTask)
        }

        dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_TASK = "arg_task"

        fun newInstance(task: Task? = null): TaskDialogFragment {
            return TaskDialogFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_TASK, task)
                }
            }
        }
    }
} 