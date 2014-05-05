module.exports = function(grunt) {

  // Project configuration.
  grunt.initConfig({
    pkg: grunt.file.readJSON('package.json'),
    concat: {
        options: {
            separator: ';'//定义一个用于插入合并输出文件之间的字符
        },
        dist: {            
            src: ['webapp/scripts/app/angular/**/*.js'],//用于连接的文件
            dest: 'webapp/scripts/app/app.js'//返回的JS文件位置
        }
    },
    uglify: {
      options: {
        banner: '/*! <%= pkg.name %> <%= grunt.template.today("yyyy-mm-dd") %> */\n'
      },
      build: {
        src: 'webapp/scripts/app/app.js',
        dest: 'webapp/scripts/app/app.min.js'
      }
    }
  });

  // Load the plugin that provides the "uglify" task.
  grunt.loadNpmTasks('grunt-contrib-uglify');
  grunt.loadNpmTasks('grunt-contrib-concat');

  // Default task(s).
  grunt.registerTask('default', ['concat', 'uglify']);

};