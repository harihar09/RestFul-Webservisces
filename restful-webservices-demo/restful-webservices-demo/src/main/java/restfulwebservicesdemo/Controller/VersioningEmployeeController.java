package restfulwebservicesdemo.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restfulwebservicesdemo.Domain.EmployeeV1;
import restfulwebservicesdemo.Domain.EmployeeV2;
import restfulwebservicesdemo.Domain.Name;

@RestController
@RequestMapping("/emp")
public class VersioningEmployeeController {

    //version v1 using new endpoint
    @GetMapping("/v1")
    public ResponseEntity employeeV1() {

        return new ResponseEntity<>(new EmployeeV1("canny"), HttpStatus.OK);
    }

    //version v2 using new endpoint
    @GetMapping("/v2")
    public ResponseEntity employeeV2() {

        return new ResponseEntity<>(new EmployeeV2(new Name("carry", "adam")), HttpStatus.OK);
    }

    //version v1 using params called version=1
    @GetMapping(path = "", params = "version=1")
    public ResponseEntity employeeV1UsingParams() {

        return new ResponseEntity<>(new EmployeeV1("tom"), HttpStatus.OK);
    }

    //version v2 using params version=2
    @GetMapping(path = "", params = "version=2")
    public ResponseEntity employeeV2UsingParams() {

        return new ResponseEntity<>(new EmployeeV2(new Name("carry", "adam")), HttpStatus.OK);
    }

    //version v1 using headers called API-VERSION=1
    @GetMapping(path = "/header", headers = "API-VERSION=1")
    public ResponseEntity employeeV1UsingHeader() {

        return new ResponseEntity<>(new EmployeeV1("JOHN"), HttpStatus.OK);
    }

    //version v2 using headers called API-VERSION=2
    @GetMapping(path = "/header", headers = "API-VERSION=2")
    public ResponseEntity employeeV2UsingHeader() {

        return new ResponseEntity<>(new EmployeeV2(new Name("carry", "adam")), HttpStatus.OK);
    }
    //version v1 using accept header called "application/v1"
    @GetMapping(path = "/accept", produces = "application/xyz.company.app-1+json")
    public ResponseEntity employeeV1UsingAcceptHeader() {

        return new ResponseEntity<>(new EmployeeV1("Clare"), HttpStatus.OK);
    }
}
