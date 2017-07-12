const gulp = require('gulp')
const htmlmin = require('gulp-htmlmin')
const uglifycss = require('gulp-uglifycss')
const concat = require('gulp-concat')
const babel = require('gulp-babel')
const uglify = require('gulp-uglify')

gulp.task('app', ['app.html', 'app.css', 'app.js', 'app.assets'])

gulp.task('app.html', function() {
  gulp.src('src/main/resources/app/**/*.html')
    .pipe(htmlmin({ collapseWhitespace: true}))
    .pipe(gulp.dest('src/main/resources/static'))
})

gulp.task('app.css', function() {
  gulp.src('src/main/resources/app/**/*.css')
    .pipe(uglifycss({ "uglyComments": true}))
    .pipe(concat('app.min.css'))
    .pipe(gulp.dest('src/main/resources/static/assets/css'))
})

gulp.task('app.js', function() {
  gulp.src('src/main/resources/app/**/*.js')
    .pipe(babel({ presets: ['es2015'] }))
    .pipe(uglify())
    .pipe(concat('app.min.js'))
    .pipe(gulp.dest('src/main/resources/static/assets/js'))
})

gulp.task('app.assets', function() {
  gulp.src('src/main/resources/app/assets/**/*.*')
    .pipe(gulp.dest('src/main/resources/static/assets'))
})
