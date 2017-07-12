const gulp = require('gulp')
const uglify = require('gulp-uglify')
const uglifycss = require('gulp-uglifycss')
const concat = require('gulp-concat')


gulp.task('deps', ['deps.js'])

gulp.task('deps.js', function() {
  gulp.src([
    'node_modules/angular/angular.min.js',
    'node_modules/angular-ui-router/release/angular-ui-router.min.js',
    'node_modules/angular-route/angular-route.min.js',
    'node_modules/angular-resource/angular-resource.min.js',
    'node_modules/lodash/dist/lodash.min.js'
  ])
  .pipe(uglify())
  .pipe(concat('deps.min.js'))
  .pipe(gulp.dest('src/main/resources/static/assets/js/'))
})
