const express = require('express');
const router = express.Router();

const courses = require('../data/coursesData');

let autoId = 1;
let courseSequence = 2000;

const generateCourseId = () => `CRS${++courseSequence}`;

router.post('/', (req, res) => {
  const {
    courseName,
    courseCode,
    level,
    duration,
    intake,
    tuitionFee,
    currency,
    university,
    status
  } = req.body;

  if (!courseName || !courseCode || !university) {
    return res.status(400).json({
      message: 'courseName, courseCode, and university are required'
    });
  }

  const exists = courses.find(c => c.courseCode === courseCode);
  if (exists) {
    return res.status(409).json({
      message: 'Course with this courseCode already exists'
    });
  }

  const newCourse = {
    id: autoId++,
    courseId: generateCourseId(),
    courseName,
    courseCode,
    level,
    duration,
    intake,
    tuitionFee,
    currency,
    university,
    status: status || 'Active',
    createdAt: new Date()
  };

  courses.push(newCourse);

  res.status(201).json({
    message: 'Course added successfully',
    course: newCourse
  });
});

router.get('/', (req, res) => {
  res.json(courses);
});

router.get('/course-id/:courseId', (req, res) => {
  const course = courses.find(
    c => c.courseId === req.params.courseId
  );

  if (!course) {
    return res.status(404).json({ message: 'Course not found' });
  }

  res.json(course);
});

module.exports = router;
