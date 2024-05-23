import mongoose from 'mongoose';
const { Schema, SchemaTypes, model } = mongoose;

/*
const blogSchema = new Schema({
    title: String,
    slug: String,
    published: Boolean,
    author: String,
    content: String,
    tags: [String],
    createdAt: Date,
    updatedAt: Date,

    comments: [{
        user: String,
        content: String,
        votes: Number
    }]
});
*/

const blogSchema = new Schema({
    title:  {
      type: String,
      required: true,
    },
    slug:  {
      type: String,
      required: true,
      lowercase: true,
    },
    published: {
      type: Boolean,
      default: false,
    },
    author: {
      type: SchemaTypes.ObjectId,
      ref: 'User',
      required: true,
    },
    content: String,
    tags: [String],
    createdAt: {
      type: Date,
      default: () => Date.now(),
      immutable: true,
    },
    updatedAt: Date,
    comments: [{
      user: {
        type: SchemaTypes.ObjectId,
        ref: 'User',
        required: true,
      },
      content: String,
      votes: Number
    }]  
});

blogSchema.pre('save', function(next) {
    this.updated = Date.now();
    next();
});

// 스키마 블로그를 만든다. model은 컬렉션 이름. 컬렉션에 저장이 될 때는 s가 붙는다.
const Blog = model('Blog', blogSchema); 
// 모델을 외부에서 쓸 수 있게끔 해준다
export default Blog;