'use strict';

angular.module('crudApp').controller('StudentController',
    ['StudentService', '$scope',  function( StudentService, $scope) {

        var self = this;
        self.student = {};
        self.students=[];

        self.submit = submit;
        self.getAllStudeents = getAllStudeents;
        self.createStudent = createStudent;
        self.updateStudent = updateStudent;
        self.removeStudent = removeStudent;
        self.editStudent = editStudent;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Submitting');
            if (self.student.id === undefined || self.student.id === null) {
                console.log('Saving New Student', self.student);
                createStudent(self.student);
            } else {
                updateStudent(self.student, self.student.id);
                console.log('student updated with id ', self.student.id);
            }
        }

        function createStudent(student) {
            console.log('About to create Student');
            StudentService.createStudent(student)
                .then(
                    function (response) {
                        console.log('Student created successfully');
                        self.successMessage = 'Student created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.student={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating student');
                        self.errorMessage = 'Error while creating student: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateStudent(student, id){
            console.log('About to update Student');
            StudentService.updateStudent(student, id)
                .then(
                    function (response){
                        console.log('Student updated successfully');
                        self.successMessage='Student updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating Student');
                        self.errorMessage='Error while updating Student '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeStudent(id){
            console.log('About to remove student with id '+id);
            StudentService.removeStudent(id)
                .then(
                    function(){
                        console.log('student '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing student '+id +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllStudents(){
            return StudentService.getAllStudeents();
        }

        function editStudent(id) {
            self.successMessage='';
            self.errorMessage='';
            StudentService.getAllStudeents(id).then(
                function (student) {
                    self.student = student;
                },
                function (errResponse) {
                    console.error('Error while removing student ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.student={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }
    ]);