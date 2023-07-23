package com.tripleenergytech.recordrefill.domain.usecase.addcylinderusecase

import com.tripleenergytech.recordrefill.common.Utils
import com.tripleenergytech.recordrefill.domain.model.ActiveCylinder
import com.tripleenergytech.recordrefill.domain.model.Cylinder
import com.tripleenergytech.recordrefill.domain.repository.CylinderRepository
import javax.inject.Inject

class CylinderUseCaseImpl @Inject constructor(private val cylinderRepository: CylinderRepository): CylinderUseCase {

    override suspend fun addCylinder(
        cylinderName: String,
        tareWeight: Float,
        tare_and_gasWeight: Float,
        gasWeight: Float
    ) {
        val cylinderRecordId = Utils.Tools.RandomIDGenerator.generateID(Utils.Tools.RandomIDGenerator.Companion.Type.CYLINDER_RECORD)
        val activeCylinderRecordId = Utils.Tools.RandomIDGenerator.generateID(Utils.Tools.RandomIDGenerator.Companion.Type.ACTIVE_CYLINDER_RECORD_ID)

        val cylinderRecord = Cylinder(
            cylinder_id = cylinderRecordId,
            cylinder_name = cylinderName,
            tare_mass = tareWeight,
            tare_and_gas_mass = tare_and_gasWeight,
            gas_amount = gasWeight,
            timestamp = System.currentTimeMillis()
        )
        val activeActiveCylinder =ActiveCylinder(
            active_cylinder_id = activeCylinderRecordId,
            cylinder_id = cylinderRecordId,
            cylinder_name=cylinderName,
            tare_weight = tareWeight,
            tare_and_gas_weight = tare_and_gasWeight,
            gas_weight = (tare_and_gasWeight-tareWeight),
            timestamp_start = System.currentTimeMillis(),
            timestamp_closed = null,
            closed = 0
        )

        cylinderRepository.apply {
            addCylinder(cylinder = cylinderRecord)
            addActiveCylinder(activeActiveCylinder)
        }

    }
}