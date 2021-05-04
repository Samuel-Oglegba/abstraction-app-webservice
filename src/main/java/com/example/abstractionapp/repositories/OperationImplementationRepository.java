package com.example.abstractionapp.repositories;

import com.example.abstractionapp.models.OperationImplementation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationImplementationRepository extends JpaRepository<OperationImplementation,Long> {

    OperationImplementation findByUuid(String uuid);

     Iterable<OperationImplementation> findByTaskId(long taskId);
    //OperationImplementation findByTaskId(long taskId);

    Iterable<OperationImplementation> findByOperationId(long operationId);

    Iterable<OperationImplementation> findByCommunicationId(long communicationId);

    @Query(value ="select * from operation_implementations where (communication = :communicationId and task = :taskId1 and task2 = :taskId2) or (communication = :communicationId and task = :taskId2 and task2=:taskId1)",nativeQuery = true)
    Iterable<OperationImplementation> findByCommunicationAndTask(@Param("communicationId") long communicationId,
                                                                 @Param("taskId1") long taskId1, @Param("taskId2") long taskId2);
}
