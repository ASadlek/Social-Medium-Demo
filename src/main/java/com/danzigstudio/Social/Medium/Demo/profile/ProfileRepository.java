package com.danzigstudio.Social.Medium.Demo.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query("select p from Profile p where p.firstName = :firstName")
    public List<Profile> findByFirstName(@Param("firstName") String firstName);

   @Query("select p from Profile p where p.lastName = :lastName")
    public List<Profile> findByLastName(@Param("lastName") String lastName);

    @Query("select p from Profile p where p.firstName = :firstName and p.lastName = :lastName")
    public List<Profile> findByFullName(@Param("firstName") String firstName, @Param("lastName") String lastName);

}
