import { Book } from './book';

describe('Book', () => {
  it('should create an instance', () => {
    // @ts-ignore
    expect(new Book()).toBeTruthy();
  });
});
