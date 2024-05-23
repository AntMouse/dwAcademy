import mongoose from 'mongoose';
import Blog from './model/Blog.js';
import User from './model/User.js';

mongoose.connect("mongodb://myLmser:abc123@localhost/lms");

/*
const article1 = new Blog({
    title: 'Awesome Post1',
    slug: 'awesome-post',
    published: true,
    content: 'This is the best post ever',
    tags: ['featured', 'announcement'],
});

await article1.save();
const firstArticle = await Blog.findOne({});
console.log(firstArticle);
console.log(article1);

// 아래 코드는 객체 생성과 저장을 한 번에 한다

const article2 = await Blog.create({
    title: 'Awesome Post2',
    slug: 'awesome-post',
    published: true,
    content: 'This is the best post ever',
    tags: ['featured', 'announcement'],
});
console.log(article2);

// 데이터 갱신 코드
article2.title = "The Most Awesomest Post3";
await article2.save();
console.log(article2);

// 데이터 검색 코드
// const article = await Blog.findById("664eaa10347c45d1fc6b2ad9").exec();
// console.log(article);

// 도큐먼트 필트 추출
const article3 = await Blog.findById("664ecea06e58c17b7332ee77", "title slug content").exec();
console.log(article3);

// 필드 타입으로 추출해도 되고, 타입으로 지정해줘도 추출이 된다.
const article4 = await Blog.findById("664ecea06e58c17b7332ee77", "Object|String|Array<String>").exec();
console.log(article4);

// 데이터 삭제
const article5 = await Blog.create({
    title: 'Awesome Post4',
    slug: 'awesome-post',
    author: 'Jesse Hall',
    published: true,
    content: 'This is the best post ever',
    tags: ['featured', 'announcement'],
});
console.log(article5);
*/

/*
// author이 Jesse Hall인 데이터를 찾아서 가장 첫 번째 데이터를 없앤다.
const blog1 = await Blog.deleteOne({ author: 'Jesse Hall' });
console.log(blog1);

// 이거는 위와 같지만, 한 개만 없애지 않고 전부다 없앤다는 차이가 있다.
const blog2 = await Blog.deleteMany({ author: "Jesse Hall" })
console.log(blog2)
*/

/* 자료 검색 등에 사용할 메서드

// 주어진 조건에 해당하는 데이터가 존재하는지 여부를 확인
const blog3 = await Blog.exists({ author: 'Jesse Hall' });
console.log(blog3);

// 주어진 조건에 해당하는 첫 번째 데이터를 반환
const blogFind = await Blog.findOne({ author: 'Jesse Hall' });
console.log(blogFind);

// 코드는 author 필드가 'Jesse Hall'인 모든 문서를 찾는 것
const blogWhere = await Blog.where('author').equals('Jesse Hall');
console.log(blogWhere);

// 특정 조건에 해당하는 문서를 검색하고, 검색된 문서의 특정 필드만 선택하여 가져오는 방법
const blog = await Blog.where('author').equals('Jesse Hall').select('title author');
console.log(blog);
*/

const user = await User.create({
    name: 'Jesse Hall',
    email: 'jesse@email.com',
});
  
const article = await Blog.create({
    title: 'Awesome Post!',
    slug: 'Awesome-Post',
    author: user._id,
    content: 'This is the best post ever',
    tags: ['featured', 'announcement'],
});

console.log(article);

const article1 = await Blog.findOne({ title: 'Awesome Post!' }).populate('author');
console.log(article1);

const article2 = await Blog.findById("664eda9b1a5fc9fed9b46cad").exec();
article2.title = "Updated Title";
await article2.save();
console.log(article2);