package pro.farmmanager.resources;

import io.vavr.control.Option;
import pro.farmmanager.infrastructure.Repository;

interface ResourceRepository extends Repository<Resource> {

    Option<Resource> findByName(String name);

}
