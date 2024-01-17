// employee-list.component.ts

import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import { Employee} from "../Model/employee";
import { EmployeeService} from "../employee-service/employee.service";
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {MatSort, MatSortModule, Sort} from "@angular/material/sort";
import {LiveAnnouncer} from "@angular/cdk/a11y";

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css'],
})
export class EmployeeListComponent implements OnInit, AfterViewInit {
  // DataSource for the Material table, with Employee type data
  dataSource = new MatTableDataSource<Employee>();

  // Array of strings defining the column names to display in the table
  displayedColumns: string[] = ['id', 'name', 'phoneNumber', 'supervisors'];

  // ViewChild to access the MatSort directive and control sorting
  @ViewChild(MatSort) sort!: MatSort;

  // Component constructor, injecting the EmployeeService and LiveAnnouncer for accessibility
  constructor(private employeeService: EmployeeService, private _liveAnnouncer: LiveAnnouncer) {}

  // OnInit lifecycle hook to fetch employee data when component initializes
  ngOnInit() {
    this.employeeService.findAll().subscribe(data => {
      this.dataSource.data = data; // Assigning fetched data to the table's data source
      this.dataSource.sort = this.sort; // Setting up sorting on data load
    });
  }

  // AfterViewInit lifecycle hook to ensure sorting is set up after view initialization
  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
  }

  // Method to apply a filter based on user input for searching through the table
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase(); // Setting the filter value on the data source
  }

  // Method to announce sort changes for accessibility, using LiveAnnouncer
  announceSortChange(sortState: Sort) {
    if (sortState.direction) {
      this._liveAnnouncer.announce(`Sorted ${sortState.direction}ending`);
    } else {
      this._liveAnnouncer.announce('Sorting cleared');
    }
  }
}
