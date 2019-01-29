'use strict';

angular.module('crudApp').factory('StudentService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllStudents: loadAllStudents,
                getAllStudents: getAllStudents,
                getStudent: getStudent,
                createStudent: createStudent,
                updateStudent: updateStudent,
                removeStudent: removeStudent
            };

            return factory;

            function loadAllStudents() {
                console.log('Fetching all Students');
                var deferred = $q.defer();
                $http.get(urls.STUDENT_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all Students');
                            $localStorage.students = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading Students');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllStudents(){
                return $localStorage.students;
            }

            function getStudent(id) {
                console.log('Fetching Student with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.STUDENT_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Student with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading student with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createStudent(student) {
                console.log('Creating student');
                var deferred = $q.defer();
                $http.post(urls.STUDENT_SERVICE_API, student)
                    .then(
                        function (response) {
                            loadAllStudent();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating Student : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateStudent(student, id) {
                console.log('Updating student with id '+id);
                var deferred = $q.defer();
                $http.put(urls.STUDENT_SERVICE_API + id, student)
                    .then(
                        function (response) {
                            loadAllStudents();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating student with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeStudent(id) {
                console.log('Removing student with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.STUDENT_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllStudents();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing Student with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);