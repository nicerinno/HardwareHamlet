package com.hardwarehamlet.hardwarehamlet.data.local;

import android.arch.persistence.room.Dao;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.hardwarehamlet.hardwarehamlet.model.Component_Type;
import com.hardwarehamlet.hardwarehamlet.model.Components;

import java.util.List;

@Dao
public interface ComponentsDAO {

    @Query("SELECT * FROM components")
    List<Components> getComponentsList();

    @Query("SELECT * FROM components WHERE component_type_id = :id ORDER BY price DESC")
    List<Components> getComponentsByType(int id);

    @Query("SELECT * FROM components ORDER BY price ASC")
    List<Components> getComponentsWithoutSearchAndTypeASC();
    @Query("SELECT * FROM components ORDER BY price DESC")
    List<Components> getComponentsWithoutSearchAndTypeDESC();

    @Query("SELECT * FROM components WHERE component_type_id = :type_id ORDER BY price ASC")
    List<Components> getComponentsWithoutSearchASC(int type_id);
    @Query("SELECT * FROM components WHERE component_type_id = :type_id ORDER BY price DESC")
    List<Components> getComponentsWithoutSearchDESC(int type_id);

    @Query("SELECT * FROM components WHERE component_id = :component_id")
    Components getComponentById(long component_id);

    @Query("SELECT * FROM components WHERE name like :name or brand like :brand ORDER BY price ASC ")
    List<Components> getAllComponentsWithoutTypeASC(String name, String brand);

    @Query("SELECT * FROM components WHERE name like :name or brand like :brand ORDER BY price DESC ")
    List<Components> getAllComponentsWithoutTypeDESC(String name, String brand);

    @Query("SELECT * FROM components WHERE component_type_id = :type AND name like :name OR brand like" +
            " :brand AND component_type_id= :type ORDER BY price ASC")
    List<Components> getAllComponentsWithTypeASC(String name, String brand, int type);

    @Query("SELECT * FROM components WHERE component_type_id = :type AND name like :name  OR brand like" +
            " :brand AND component_type_id= :type ORDER BY price DESC")
    List<Components> getAllComponentsWithTypeDESC(String name, String brand, int type);

    @Query("SELECT * FROM component_type")
    List<Component_Type> getComponentTypeList();

    @Insert
    void insertComponentTypeList(List<Component_Type> component_typeList);

    @Query("DELETE FROM components")
    void deleteAllComponents();

    @Insert
    void insertComponents(List<Components> componentsList);

    @Query("DELETE FROM component_type")
    void deleteComponentTypeList();
}
