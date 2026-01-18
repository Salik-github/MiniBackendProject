const express = require('express');
const router = express.Router();

const students = require('../data/studentsData');
const applications = require('../data/applicationsData');

let currentId = 1;

// CREATE student
router.post('/', (req, res) => {
  const {
    firstName,
    lastName,
    email,
    mobile,
    dateOfBirth,
    gender,
    country
  } = req.body;

  if (!firstName || !lastName || !email || !mobile) {
    return res.status(400).json({
      message: 'firstName, lastName, email, and mobile are required'
    });
  }

  const newStudent = {
    students_id: currentId++,
    firstName,
    lastName,
    email,
    mobile,
    dateOfBirth,
    gender,
    country,
    createdAt: new Date()
  };

  students.push(newStudent);

  res.status(201).json({
    message: 'Student added successfully',
    student: newStudent
  });
});

// GET all students
router.get('/', (req, res) => {
  res.json(students);
});

// GET student by ID (WITH applications)
router.get('/:id', (req, res) => {
  const studentId = parseInt(req.params.id);

  const student = students.find(s => s.students_id === studentId);
  if (!student) {
    return res.status(404).json({ message: 'Student not found' });
  }

  const studentApplications = applications.filter(
    app => app.studentId === studentId
  );

  res.json({
    ...student,
    applicationCount: studentApplications.length,
    applications: studentApplications
  });
});

module.exports = router;
