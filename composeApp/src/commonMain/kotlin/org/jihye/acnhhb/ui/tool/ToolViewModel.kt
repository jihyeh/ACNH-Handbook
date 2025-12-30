package org.jihye.acnhhb.ui.tool

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.jihye.acnhhb.domain.model.Tool
import org.jihye.acnhhb.domain.repository.ToolRepository

class ToolViewModel(
    toolRepository: ToolRepository,
) : ViewModel() {

    val state: StateFlow<ToolListState> =
        toolRepository
            .getTools()
            .map { tools -> ToolListState.Success(tools) as ToolListState }
            .catch { exception ->
                emit(ToolListState.Error(exception.message ?: "Unknown error"))
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = ToolListState.Loading,
            )
}

sealed interface ToolListState {
    data object Loading : ToolListState
    data class Success(val tools: List<Tool>) : ToolListState
    data class Error(val message: String) : ToolListState
}
