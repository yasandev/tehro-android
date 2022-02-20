package dev.yasan.metro.tehran.data.repo.intersection

import dev.yasan.metro.tehran.data.db.dao.IntersectionDAO
import dev.yasan.metro.tehran.data.db.entity.Intersection
import javax.inject.Inject

/**
 * The main implementation of [IntersectionRepository].
 */
class IntersectionRepositoryImp @Inject constructor(
    private val intersectionDAO: IntersectionDAO
) : IntersectionRepository {

    override suspend fun getIntersection(intersectionId: Int): Intersection? {
        return intersectionDAO.getById(intersectionId = intersectionId)
    }

    override suspend fun getIntersectionByStationId(stationId: Int): Intersection? {
        return intersectionDAO.getByStationId(stationId = stationId)
    }

}
