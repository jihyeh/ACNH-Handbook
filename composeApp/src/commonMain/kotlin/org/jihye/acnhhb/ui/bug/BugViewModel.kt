package org.jihye.acnhhb.ui.bug

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import org.jihye.acnhhb.domain.model.Bug
import org.jihye.acnhhb.domain.repository.BugRepository

class BugViewModel(
    bugRepository: BugRepository,
) : ViewModel() {
    val state: StateFlow<BugListState> =
        bugRepository
            .getBugs()
            .map { BugListState.Success(it) as BugListState }
            .onStart { emit(BugListState.Loading) }
            .catch { emit(BugListState.Error(it.message ?: "Unknown error")) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = BugListState.Loading,
            )
}

sealed interface BugListState {
    data object Loading : BugListState
    data class Success(val bugs: List<Bug>) : BugListState
    data class Error(val message: String) : BugListState
}
