const gulp = require('gulp')
const util = require('gulp-util')

require('./gulpTasks/app')
require('./gulpTasks/deps')

gulp.task('build', function() {
    gulp.start('deps', 'app')
})
