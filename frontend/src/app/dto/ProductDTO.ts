export class ResponseProductDTO {
  constructor(
    public id: number,
    public productName: string,
    public brandId: number,
    public categoryId: number,
    public modelYear: number,
    public listPrice: number
  ) {}
}

export class ProductDTO {
    constructor(
        public id: number,
        public productName: string,
        public modelYear: number,
        public listPrice: number,
        public brandName: string,
        public categoryName: string
    ) {}
}
