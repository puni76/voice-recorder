package com.example.recorderjetpackcompose.viewModels

import android.media.AudioRecord
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recorderjetpackcompose.di.repo.RecorderRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordingsViewModel @Inject constructor(
    private val repository: RecorderRepo
) : ViewModel() {

    fun getRecordData(query: String?): Flow<List<AudioRecord>> {
        return if (query != null && query.isNotEmpty()) {
            repository.getAllAudioRecords(
                query
            )
        } else {
            repository.getRecords()
        }
    }

    fun insert(audioRecord: com.example.recorderjetpackcompose.database.model.AudioRecord) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertRecording(audioRecord)
    }

    fun update(audioRecord: AudioRecord) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateRecording(audioRecord)
    }

    fun delete(audioRecord: AudioRecord) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteRecording(audioRecord)
    }

    suspend fun deleteAllRecordings() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllAudioRecords()
    }
}
