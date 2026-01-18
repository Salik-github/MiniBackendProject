const express = require('express');
const router = express.Router();

// Mock DB / In-memory data
const students = require('../data/studentsData');
const courses = require('../data/coursesData');
const applications = require('../data/applicationsData');

// Application ID sequence
let appSeq = 3000;
const generateAppId = () => `APP${++appSeq}`;

/**
 * ============================
 * APPLY FOR A COURSE
 * POST /api/applications
 * ============================
 */
router.post('/', (req, res) => {
  const { studentId, courseId } = req.body;

  // 1. Validate request body
  if (!studentId || !courseId) {
    return res.status(400).json({
      message: 'studentId and courseId are required'
    });
  }

  // 2. Validate student
  const student = students.find(
    s => Number(s.students_id) === Number(studentId)
  );
  if (!student) {
    return res.status(404).json({
      message: 'Student not found'
    });
  }

  // 3. Validate course
  const course = courses.find(
    c => c.courseId === courseId
  );
  if (!course) {
    return res.status(404).json({
      message: 'Course not found'
    });
  }

  // 4. Prevent duplicate application
  const alreadyApplied = applications.find(
    a => Number(a.studentId) === Number(studentId) && a.courseId === courseId
  );
  if (alreadyApplied) {
    return res.status(409).json({
      message: 'Student already applied for this course'
    });
  }

  // 5. Create application
  const application = {
    applicationId: generateAppId(),
    studentId,
    studentName: `${student.firstName} ${student.lastName}`,
    courseId,
    courseName: course.courseName,
    university: course.university,
    intake: course.intake,
    status: 'PENDING',
    appliedAt: new Date().toISOString()
  };

  applications.push(application);

  // 6. Success response
  return res.status(201).json({
    message: 'Application submitted successfully',
    application
  });
});

/**
 * ============================
 * GET ALL APPLICATIONS
 * GET /api/applications
 * ============================
 */
router.get('/', (req, res) => {
  res.status(200).json(applications);
});

/**
 * ============================
 * GET APPLICATION BY ID
 * GET /api/applications/:applicationId
 * ============================
 */
router.get('/:applicationId', (req, res) => {
  const { applicationId } = req.params;

  const application = applications.find(
    a => a.applicationId === applicationId
  );

  if (!application) {
    return res.status(404).json({
      message: 'Application not found'
    });
  }

  res.status(200).json(application);
});

module.exports = router;
