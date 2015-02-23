var gulp = require('gulp');
var browserify = require('browserify');
var source = require('vinyl-source-stream');
var join = require('path').resolve;
var reactify = require('reactify');
var less = require('gulp-less');
var rename = require('gulp-rename');
var plumber = require('gulp-plumber');
var notify = require('gulp-notify');
var karma = require('gulp-karma');

gulp.task('default', ['less', 'jsx']);
gulp.task('watch', ['default', 'watch-less', 'watch-jsx']);
gulp.task('test', ['test-javascript']);

var errorHandler = {
  errorHandler: notify.onError('Error: <%- error.message%>')
};
var outputFolder = join('../src/main/resources/www/dist');

gulp.task('jsx', function() {
  var b = browserify();

  b.transform('reactify');

  b.add(join('app/app.jsx'));
  b.bundle()
    .on('error', function(err) {
      errorHandler.errorHandler(err);
    })
    .pipe(source('app.bundle.js'))
    .pipe(gulp.dest(outputFolder))
    .pipe(notify('Successfully compiled JavaScript!'));
});

gulp.task('less', function() {
  gulp.src(join('style/styles.less'))
    .pipe(plumber(errorHandler))
    .pipe(less())
    .pipe(rename({
      suffix: '.bundle'
    }))
    .pipe(gulp.dest(outputFolder))
    .pipe(notify('Successfully compiled Less!'));
});


gulp.task('watch-less', function() {
  gulp.watch(join('style/**/*.less'), ['less']);
});

gulp.task('watch-jsx', function() {
  gulp.watch(join('app/**/*.js*'), ['jsx']);
});

var testFiles = ['app/js/**/*.js'];

gulp.task('test-javascript', function() {
  return gulp.src(testFiles)
    .pipe(karma({
      configFile: 'karma.conf.js',
      action: 'run'
    }))
    .on('error', function(err) {
      throw err;
    });
});

gulp.task('watch-test', function() {
  gulp.src(testFiles)
    .pipe(karma({
      configFile: 'karma.conf.js',
      action: 'watch'
    }));
});
