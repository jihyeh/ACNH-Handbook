package org.jihye.acnhhb.ui.villager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import org.jihye.acnhhb.domain.model.Villager
import org.jihye.acnhhb.domain.repository.VillagerRepository

class VillagerViewModel(
    villagerRepository: VillagerRepository,
) : ViewModel() {
    val state: StateFlow<VillagerListState> =
        villagerRepository
            .getVillagers()
            .map { VillagerListState.Success(it) as VillagerListState }
            .onStart { emit(VillagerListState.Loading) }
            .catch { emit(VillagerListState.Error(it.message ?: "Unknown error")) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = VillagerListState.Loading,
            )
}

sealed interface VillagerListState {
    data object Loading : VillagerListState
    data class Success(val villagers: List<Villager>) : VillagerListState
    data class Error(val message: String) : VillagerListState
}
